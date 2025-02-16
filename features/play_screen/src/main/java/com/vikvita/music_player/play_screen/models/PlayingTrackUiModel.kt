package com.vikvita.music_player.play_screen.models

 data class PlayingTrackUiModel(
    val id:String,
    val songTitle:String,
    val albumTitle:String,
    val artistName:String,
    val picture:String?,
    val source:String
)