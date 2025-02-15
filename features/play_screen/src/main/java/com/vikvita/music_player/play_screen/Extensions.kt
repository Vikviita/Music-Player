package com.vikvita.music_player.play_screen


import com.vikvita.music_player.domain.models.Track
import com.vikvita.music_player.play_screen.models.PlayingTrackUiModel

internal fun Track.toPlayTrackUiModel(): PlayingTrackUiModel {
    return PlayingTrackUiModel(
        id = id,
        songTitle = trackTitle,
        albumTitle = albumName,
        artistName = artistName,
        picture = bigPicture,
        source = source
    )
}

internal fun List<Track>.toListPlayingTrackUiModel(): List<PlayingTrackUiModel> =
    map { it.toPlayTrackUiModel() }