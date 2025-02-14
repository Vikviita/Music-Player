package com.vikvita.music_player.track_list

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vikvita.music_player.track_list.models.TrackUiModel

internal class TrackPreviewParametrProvider : PreviewParameterProvider<TrackUiModel> {
    private val trackUiModel = TrackUiModel(
        id = "111111",
        title = "Skyfall",
        author = "Adele",
        cover = "https://cdn-images.dzcdn.net/images/artist/e5fc443d2abc03b487234ba4de65a001/56x56-000000-80-0-0.jpg"
    )
    override val values: Sequence<TrackUiModel> = sequenceOf(
        trackUiModel
    )

}

internal class TrackListPreviewParametrProvider : PreviewParameterProvider<List<TrackUiModel>> {
    override val values: Sequence<List<TrackUiModel>> = sequenceOf(
        listOf(
            TrackUiModel(
                id = "1",
                title = "Skyfall",
                author = "Adele",
                cover = "https://cdn-images.dzcdn.net/images/artist/e5fc443d2abc03b487234ba4de65a001/56x56-000000-80-0-0.jpg"
            ),
            TrackUiModel(
                id = "2",
                title = "Skyfall",
                author = "Adele",
                cover = "https://cdn-images.dzcdn.net/images/artist/e5fc443d2abc03b487234ba4de65a001/56x56-000000-80-0-0.jpg"
            ),
            TrackUiModel(
                id = "3",
                title = "Skyfall",
                author = "Adele",
                cover = "https://cdn-images.dzcdn.net/images/artist/e5fc443d2abc03b487234ba4de65a001/56x56-000000-80-0-0.jpg"
            ),
            TrackUiModel(
                id = "4",
                title = "Skyfall",
                author = "Adele",
                cover = "https://cdn-images.dzcdn.net/images/artist/e5fc443d2abc03b487234ba4de65a001/56x56-000000-80-0-0.jpg"
            )
        )

    )

}