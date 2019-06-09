package com.esp.localjobs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.esp.localjobs.R
import com.esp.localjobs.adapters.UserItem
import com.esp.localjobs.data.models.RequestToJob
import com.esp.localjobs.utils.AnimationsUtils
import com.esp.localjobs.viewModels.JobRequestViewModel
import com.esp.localjobs.viewModels.LoginViewModel
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_job_details.*
import kotlinx.android.synthetic.main.fragment_job_details.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Fragment used to display the details of a job.
 * Params:
 * jobID: String -> the ID of a job
 */

@InternalCoroutinesApi
class JobDetailsFragment : Fragment(), CoroutineScope {
    private val args: JobDetailsFragmentArgs by navArgs()
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val jobRequestViewModel: JobRequestViewModel by activityViewModels()
    private val jobId by lazy { args.job.id }
    private val job by lazy { args.job }

    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_job_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mJob = Job()
        setupSharedElementsTransactions()
        postponeEnterTransition()

        // This callback will only be called when MyFragment is at least Started.
        setupBackAnimations()
    }

    private fun setupBackAnimations() {
        requireActivity().onBackPressedDispatcher
            .addCallback(this, OnBackPressedCallback {
                // Handle the back button event
                prepareUiToGoBack()
                true
            })
    }

    private fun prepareUiToGoBack() {
        AnimationsUtils.popout(fabMap)
        AnimationsUtils.popout(contact_fab) {
            findNavController().popBackStack()
        }
    }

    private fun setupSharedElementsTransactions() {
        val trans = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = trans
        sharedElementReturnTransition = trans
        allowReturnTransitionOverlap = false
        allowEnterTransitionOverlap = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load("https://picsum.photos/400").into(view.imageView)
        view.title.text = args.job.title
        view.description.text = args.job.description
        setupTransitionName(view)
        setupFabButton(view)
        setupMapFab(view)
        setupInterestedList()
        AnimationsUtils.popup(contact_fab, 400)
        AnimationsUtils.popup(fabMap, 200)
        startPostponedEnterTransition()
    }

    private fun setupTransitionName(view: View) {
        view.imageView.transitionName = "image_${job.uid}"
        view.title.transitionName = "title_${job.uid}"
        view.description.transitionName = "description_${job.uid}"
    }

    fun setupMapFab(view: View) {
        view.fabMap.setOnClickListener {
            findNavController().navigate(
                R.id.action_destination_map_to_destination_single_map,
                Bundle().apply { putParcelable("job", args.job) })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        jobRequestViewModel.stopListeningForChanges(args.job.id)
    }
    //  TODO Se la persona ha già inviato la disponibilità, il testo dev'essere "contacted"
    // TODO check if, rather than hiding the button, the visibility can be set to "disabled" (like grey button)
    /**
     * Setup contact fab button.
     * If the user isn't logged or the user owns the job, the button is set to invisible.
     */
    private fun setupFabButton(view: View) = launch {
        val currentUserId = loginViewModel.getUserId()
        val jobOwner = args.job.uid
        if (currentUserId == null || args.job.uid == null) {
            return@launch
        }

        val hasSentInterest = jobRequestViewModel.hasSentInterest(currentUserId, jobId)
        if (!isActive) return@launch
        if (hasSentInterest) {
            view.contact_fab.text = getString(R.string.contacted)
            view.contact_fab.isEnabled = false
        }
        /*
         //commented for testing
         if (currentUserId == null || jobOwner == currentUserId) {
             contact_fab.visibility = View.GONE
             return
         }*/

        val request = RequestToJob(
            job_publisher_id = jobOwner ?: "",
            name = loginViewModel.getUserName() ?: "",
            interested_user_id = currentUserId,
            job_id = args.job.id
        )
        view.contact_fab.setOnClickListener {
            jobRequestViewModel.addRequest(
                args.job.id,
                request
            )
        }
    }

    // todo only for the person who created the job
    private fun setupInterestedList() = with(jobRequestViewModel) {
        startListeningForChanges(args.job.id)
        getInterestedUserLiveData(args.job.id).observe(this@JobDetailsFragment,
            androidx.lifecycle.Observer {
                setUsersInList(it)
            })
    }

    private fun setUsersInList(usersIds: List<String>) {
        interestedTitle.visibility = if (usersIds.isEmpty()) View.INVISIBLE else View.VISIBLE
        interestedList.adapter = GroupAdapter<ViewHolder>().apply {
            addAll(usersIds.map { UserItem(it) })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // hide other icons (like current user profile
        menu.forEach { it.isVisible = false }
        // if the user owns the job allow him to edit it
        if (args.job.uid != loginViewModel.getUserId())
            return
        inflater.inflate(R.menu.menu_edit, menu)
        val editMenu = menu.findItem(R.id.menu_edit_item)
        editMenu.setOnMenuItemClickListener {
            val action =
                JobDetailsFragmentDirections.actionDestinationJobDetailsToDestinationEdit(args.job)
            findNavController().navigate(action.actionId, action.arguments)
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
