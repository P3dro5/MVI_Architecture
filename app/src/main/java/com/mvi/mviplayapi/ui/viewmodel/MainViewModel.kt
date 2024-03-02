package com.mvi.mviplayapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.mviplayapi.data.repository.GameRepository
import com.mvi.mviplayapi.ui.intent.GamesIntent
import com.mvi.mviplayapi.ui.viewstate.DataState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: GameRepository
) : ViewModel() {

    val dataIntent = Channel<GamesIntent>(Channel.UNLIMITED)
    val dataState = MutableStateFlow<DataState>(DataState.Inactive)


    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            dataIntent.consumeAsFlow().collect {
                when (it) {
                    is GamesIntent.FetchData -> fetchData()
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            dataState.value = DataState.Loading
            dataState.value = try {
                DataState.ResponseData(repository.getGames())
            } catch (e: Exception) {
                DataState.ApiError(e.localizedMessage)
            }
        }
    }
}