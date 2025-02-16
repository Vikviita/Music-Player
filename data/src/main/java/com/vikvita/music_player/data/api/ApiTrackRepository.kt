package com.vikvita.music_player.data.api

import com.vikvita.music_player.domain.TrackRepository
import com.vikvita.music_player.domain.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
/**
 * Реализация [TrackRepository] для работы с api
 * */
internal class ApiTrackRepository @Inject constructor(
    private val networkService: NetworkService
) : TrackRepository {
    override suspend fun getInitialList(): Result<List<Track>> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            networkService.getInitialTrackList().tracks.data.toDomainTrackList()
        }
    }

    override suspend fun findTrackByName(name: String): Result<List<Track>> =
        withContext(Dispatchers.IO) {
            runCatching {
                networkService.findTrackByName(name).data.toDomainTrackList()
            }
        }

    override suspend fun getTrackById(id: String): Result<Track> = withContext(Dispatchers.IO) {
        runCatching {
            networkService.getTrackById(id).toDomainTrackModel()
        }
    }

    override suspend fun getTracksByAlbum(id: String): Result<List<Track>> =
        withContext(Dispatchers.IO) {
            runCatching {
                networkService.getTrackListByAlbum(id).tracks.data.toDomainTrackList()
            }
        }
}