package com.vikvita.music_player.data.api.models

internal data class TrackApiModel(
    val id:String,
    val title:String,
    val preview:String,
    val artist:ArtistApiModel,
    val album:AlbumApiModel
)