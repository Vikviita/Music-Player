package com.vikvita.music_player.track_list

import com.vikvita.music_player.domain.models.Track
import com.vikvita.music_player.track_list.models.TrackUiModel

internal fun Track.toTrackUiModel(): TrackUiModel {
    return TrackUiModel(
        id = this.id,
        title = this.trackTitle,
        author = this.artistName,
        cover = this.picture
    )
}


internal fun List<Track>.toListTrackUiModel(): List<TrackUiModel> = this.map { it.toTrackUiModel() }

