package com.vikvita.music_player.data.api

import com.vikvita.music_player.data.api.models.TrackApiModel
import com.vikvita.music_player.domain.models.Track

internal fun TrackApiModel.toDomainTrackModel():Track{
    return Track(
        id = this.id,
        albumId = this.album.id,
        trackTitle = this.title,
        albumName = this.album.title,
        artistName = this.artist.name,
        picture = this.album.coverSmall,
        mediumPicture = this.album.coverMedium,
        bigPicture = this.album.coverBig,
        source = this.preview
    )
}

internal fun List<TrackApiModel>.toDomainTrackList():List<Track> = this.map { it.toDomainTrackModel() }