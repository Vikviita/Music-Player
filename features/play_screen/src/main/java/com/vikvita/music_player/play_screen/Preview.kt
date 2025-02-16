package com.vikvita.music_player.play_screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vikvita.music_player.play_screen.models.PlayingTrackUiModel


internal class PlayingTrackUiModelProvider():PreviewParameterProvider<PlayingTrackUiModel>{
    override val values: Sequence<PlayingTrackUiModel> = sequenceOf(
        PlayingTrackUiModel(
            id="",
            albumTitle = "album mock",
            songTitle = "song title mock",
            artistName = "artist name mock",
            source = "",
            picture = null

        )
    )

}