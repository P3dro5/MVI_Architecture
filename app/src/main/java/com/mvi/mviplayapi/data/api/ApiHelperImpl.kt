package com.mvi.mviplayapi.data.api

import com.mvi.mviplayapi.data.model.RecentGames

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getGames(): RecentGames {
        return apiService.getGames()
    }
}