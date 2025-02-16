package com.vikvita.music_player.domain

import com.vikvita.music_player.domain.models.Track
/**
 * Общий интерфейс с логикой работы
 * */
interface TrackRepository {
    suspend fun getInitialList():Result<List<Track>>
    suspend fun findTrackByName(name:String):Result<List<Track>>

    suspend fun getTrackById(id:String):Result<Track>
    suspend fun getTracksByAlbum(id:String):Result<List<Track>>
}