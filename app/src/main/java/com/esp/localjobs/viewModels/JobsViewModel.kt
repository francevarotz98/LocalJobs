package com.esp.localjobs.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esp.localjobs.data.base.BaseRepository
import com.esp.localjobs.data.models.Job
import com.esp.localjobs.data.models.Localizable
import com.esp.localjobs.data.repository.JobsRepository

class JobsViewModel : ViewModel() {

    private val _jobs = MutableLiveData<List<Job>?>()
    private var repository = JobsRepository()

    val jobs: LiveData<List<Job>?>
        get() = _jobs

    fun loadJobs(filter: JobsRepository.JobFilter) {

        repository.jobFilter = filter

        when (filter.location) {
            null ->
                repository.addListener(object : BaseRepository.RepositoryCallback<Job> {
                    override fun onSuccess(result: List<Job>) {
                        _jobs.postValue(result)
                    }

                    override fun onError(e: Exception) {
                        _jobs.postValue(null)
                    }
                })
            else ->
                repository.addLocationListener(
                    filter.location as Localizable,
                    filter.range.toDouble(),
                    object : BaseRepository.RepositoryCallback<Job> {
                        override fun onSuccess(result: List<Job>) {
                            _jobs.postValue(result)
                        }

                        override fun onError(e: Exception) {
                            _jobs.postValue(null)
                        }
                    }
                )
        }
    }
}