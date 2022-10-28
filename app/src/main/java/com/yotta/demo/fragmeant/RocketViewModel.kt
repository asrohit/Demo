package com.yotta.demo.fragmeant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yotta.demo.`class`.RocketRepository
import com.yotta.demo.constants.Coroutines
import com.yotta.demo.model.Rockets
import kotlinx.coroutines.Job

class RocketViewModel(
    private val repository : RocketRepository
) : ViewModel() {

    private lateinit var job : Job

    private val _movies = MutableLiveData<List<Rockets>>()
    val rockets : LiveData<List<Rockets>>
        get() = _movies

    fun getRockets()
    {
        job = Coroutines.ioMain(
            {
                repository.getRocket()
            },
            {
                _movies.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}