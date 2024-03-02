package com.mvi.mviplayapi.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvi.mviplayapi.data.api.ApiHelper
import com.mvi.mviplayapi.data.repository.GameRepository
import com.mvi.mviplayapi.ui.viewmodel.MainViewModel

class MainViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(GameRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}