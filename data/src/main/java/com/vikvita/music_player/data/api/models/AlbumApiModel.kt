package com.vikvita.music_player.data.api.models

import com.google.gson.annotations.SerializedName

internal data class AlbumApiModel(
    val id: String,
    val title:String,
    @SerializedName("cover_small") val coverSmall: String?,
    @SerializedName("cover_medium") val coverMedium: String?,
    @SerializedName("cover_big") val coverBig: String?
)