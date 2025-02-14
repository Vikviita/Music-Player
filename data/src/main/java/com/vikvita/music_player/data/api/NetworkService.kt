package com.vikvita.music_player.data.api


import com.vikvita.music_player.data.api.models.TrackDataApiModel
import com.vikvita.music_player.data.api.models.TrackListApiModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NetworkService {
    @GET("chart")
    suspend fun getInitialTrackList(): TrackDataApiModel

    @GET("search")
   suspend fun findTrackByName(@Query("q") name: String): TrackListApiModel
}