package com.esp.localjobs.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esp.localjobs.data.base.BaseRepository
import com.esp.localjobs.data.base.FirebaseDatabaseRepository
import com.esp.localjobs.data.models.Job
import com.esp.localjobs.data.repository.JobsRepository

class JobsViewModel : ViewModel() {

    private val _jobs = MutableLiveData<List<Job>?>()
    private var repository: BaseRepository<Job> = JobsRepository()

    val jobs: LiveData<List<Job>?>
        get() = _jobs

    fun loadJobs() {
        repository.addListener(object : FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<Job> {
            override fun onSuccess(result: List<Job>) {
                _jobs.postValue(result)
            }

            override fun onError(e: Exception) {
                _jobs.postValue(null)
            }
        }) { collectionToFilter ->
            // here we can do filtering
            collectionToFilter
        }
    }
}