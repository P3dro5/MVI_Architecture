package com.mvi.mviplayapi.data.api

import com.mvi.mviplayapi.data.model.RecentGames

interface ApiHelper {
    suspend fun getGames(): RecentGames
}