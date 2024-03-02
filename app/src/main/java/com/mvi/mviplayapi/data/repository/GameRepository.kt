package com.mvi.mviplayapi.data.repository

import com.mvi.mviplayapi.data.api.ApiHelper

class GameRepository(private val apiHelper: ApiHelper) {
        suspend fun getGames() = apiHelper.getGames()
}