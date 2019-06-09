package com.esp.localjobs.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esp.localjobs.LocalJobsApplication
import com.esp.localjobs.data.models.Location
import com.esp.localjobs.data.repository.JobsRepository.JobFilter
import com.esp.localjobs.data.repository.MAX_RANGE_KM
import com.esp.localjobs.utils.GeocodingUtils
import com.esp.localjobs.utils.PositionManager

/**
 * Shared view model between filter, jobs and proposals fragment.
 * When the user asks to filter results @variable userRequestedFilterResults is set to true.
 *
 * Will probably be replaced with something lighter
 */
class FilterViewModel : ViewModel() {

    private val _activeFilters = MutableLiveData<JobFilter>()
    val activeFilters: LiveData<JobFilter>
        get() = _activeFilters

    val range: Int
        get() = activeFilters.value?.range ?: MAX_RANGE_KM

    val location: Location?
        get() = activeFilters.value?.location

    val salary: Float?
        get() = activeFilters.value?.salary

    init {
        val context = LocalJobsApplication.applicationContext()
        PositionManager.getLastKnownPosition(context)?.let {
            val city = GeocodingUtils.coordinatesToCity(context, it.latitude, it.longitude)
            val location = Location(it.latitude, it.longitude, city)

            setFilters(JobFilter(
                location = location,
                filteringJobs = true,
                range = range,
                salary = salary
            ))
        } ?: setFilters(JobFilter(filteringJobs = true, range = range, salary = salary))
    }

    fun setFilters(newFilters: JobFilter) {
        _activeFilters.postValue(newFilters)
    }

    fun setQuery(newQuery: String) {
        _activeFilters.postValue(
            activeFilters.value!!.apply {
                query = newQuery
            }
        )
    }

    fun setLocation(newLocation: Location) {
        _activeFilters.postValue(
            activeFilters.value!!.apply {
                location = newLocation
            }
        )
    }
}
