package com.vikvita.music_player.domain.models

data class Track(
    val id:String,
    val albumId:String,
    val trackItem:String,
    val albumName:String,
    val artisName:String,
    val picture:String,
    val source:String
)