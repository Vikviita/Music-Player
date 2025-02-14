package com.vikvita.music_player.domain.models

data class Track(
    val id:String,
    val albumId:String,
    val trackTitle:String,
    val albumName:String,
    val artistName:String,
    val picture:String?,
    val mediumPicture:String?,
    val bigPicture:String?,
    val source:String
)