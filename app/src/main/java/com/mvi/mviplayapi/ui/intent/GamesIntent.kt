package com.mvi.mviplayapi.ui.intent

sealed class GamesIntent {
    data object FetchData: GamesIntent()
}