package com.vikvita.music_player.track_list.models

internal data class ActionButtonParams(
    val text: String,
    val onClick: () -> Unit
)
