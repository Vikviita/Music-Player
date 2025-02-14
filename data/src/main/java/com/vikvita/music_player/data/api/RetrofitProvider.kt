package com.vikvita.music_player.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.deezer.com/"

internal class RetrofitProvider {
    val networkService: NetworkService = createService()


    private fun createService(): NetworkService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(NetworkService::class.java)
    }
}