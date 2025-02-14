package com.vikvita.music_player.data.local

import com.vikvita.music_player.data.local.models.LocalTrackModel
import com.vikvita.music_player.domain.models.Track

internal fun LocalTrackModel.toDomainTrack(id:String):Track{
    return Track(
        id=id,
        albumId="",
        trackTitle = this.title,
        albumName = this.albumName,
        artistName = this.authorName,
        picture = this.cover,
        mediumPicture = this.cover,
        bigPicture = this.cover,
        source = this.path
    )
}



internal fun List<LocalTrackModel>.toDomainTrackList():List<Track>{
     return this.mapIndexed { index, localTrackModel ->
          localTrackModel.toDomainTrack(index.toString())
      }
}