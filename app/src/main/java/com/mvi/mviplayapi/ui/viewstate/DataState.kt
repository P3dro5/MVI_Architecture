package com.mvi.mviplayapi.ui.viewstate

import com.mvi.mviplayapi.data.model.RecentGames

sealed class DataState {
    object Inactive: DataState()
    object Loading: DataState()
    data class ResponseData(val data: RecentGames): DataState()
    data class ApiError(val error: String?): DataState()
}