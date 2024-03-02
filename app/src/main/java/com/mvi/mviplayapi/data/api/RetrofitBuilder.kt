package com.mvi.mviplayapi.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitBuilder {

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(URL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    private object URL {
        const val BASE_URL = "https://api.rawg.io/api/"
    }
}