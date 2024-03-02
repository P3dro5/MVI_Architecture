package com.mvi.mviplayapi.data.api

import com.mvi.mviplayapi.BuildConfig
import com.mvi.mviplayapi.data.model.RecentGames
import retrofit2.http.GET

interface ApiService {
    @GET("games?key="+ BuildConfig.apikey + "&dates=2016-12-01,2016-12-30&platforms=18,1,7")
    suspend fun getGames(): RecentGames
}