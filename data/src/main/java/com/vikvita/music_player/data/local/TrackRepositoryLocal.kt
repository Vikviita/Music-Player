package com.vikvita.music_player.data.local


import com.vikvita.music_player.data.local.models.LocalTrackModel
import com.vikvita.music_player.domain.TrackRepository
import com.vikvita.music_player.domain.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Репозиторий для работы с локальными данными
 * */
internal class TrackRepositoryLocal @Inject constructor(
    private val mediaReader: MediaReader
) : TrackRepository {
    private var listOfTrack: List<LocalTrackModel> = listOf()

    override suspend fun getInitialList(): Result<List<Track>> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            listOfTrack = mediaReader.getAllAudioFromDevice()
            listOfTrack.toDomainTrackList()
        }
    }

    override suspend fun findTrackByName(name: String): Result<List<Track>> = kotlin.runCatching {
        listOfTrack.filter { it.title.contains(name) || it.authorName.contains(name) }
            .toDomainTrackList()
    }

    override suspend fun getTrackById(id: String): Result<Track> =
        kotlin.runCatching {
            listOfTrack[id.toInt()].toDomainTrack(id)
        }


    override suspend fun getTracksByAlbum(id: String): Result<List<Track>> = kotlin.runCatching {
        listOfTrack.ifEmpty { throw EmptyList }.toDomainTrackList()
    }
}


object EmptyList : Throwable("list of tracks is empty")