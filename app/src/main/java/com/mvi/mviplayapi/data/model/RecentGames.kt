package com.mvi.mviplayapi.data.model

data class RecentGames(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Game>,
    val user_platforms: Boolean,
)