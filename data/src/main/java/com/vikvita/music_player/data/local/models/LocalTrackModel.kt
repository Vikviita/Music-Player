package com.vikvita.music_player.data.local.models

data class LocalTrackModel(
    val title:String,
    val authorName:String,
    val albumName:String,
    val cover:String?,
    val path:String
)