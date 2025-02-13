package com.vikvita.music_player.domain

import com.vikvita.music_player.domain.models.Track

interface TrackRepository {
    suspend fun getInitialList():Result<List<Track>>
    suspend fun findTrackByName(name:String):Result<List<Track>>
}