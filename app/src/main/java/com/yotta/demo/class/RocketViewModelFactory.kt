package com.yotta.demo.`class`

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yotta.demo.fragmeant.RocketViewModel

class RocketViewModelFactory (
    private val repositry : RocketRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RocketViewModel(repositry) as T
    }
}