package com.vikvita.music_player.domain.interactor

import com.vikvita.music_player.domain.TrackRepository
import com.vikvita.music_player.domain.models.Track
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayTrackInteractor(
    private val trackRepository: TrackRepository
) {
    private val _currentTrack = MutableSharedFlow<Track>()
    private val _loadingTrackStatus = MutableStateFlow<LoadStatus>(LoadStatus.Initial)
    private var trackList:List<Track> = listOf()
    private var currentTrackIndex = -1
    private val _isPrevAndNextTrackAvailable = MutableStateFlow(false)
    val currentTrack = _currentTrack.asSharedFlow()
    val loadingTrackStatus = _loadingTrackStatus.asStateFlow()
    val isPrevAndNextTrackAvailable = _isPrevAndNextTrackAvailable.asStateFlow()

    suspend fun getTrackById(id: String) {
        _loadingTrackStatus.emit(LoadStatus.InProgress)
        val trackResult = trackRepository.getTrackById(id)
        if (trackResult.isSuccess){
            val track = trackResult.getOrNull()!!
            _currentTrack.emit(track)
            _loadingTrackStatus.emit(LoadStatus.Success)
            val trackListByAlbum = trackRepository.getTracksByAlbum(track.albumId)
            if(trackListByAlbum.isSuccess){
                trackList = trackListByAlbum.getOrNull()!!
                currentTrackIndex = trackList.indexOfFirst {it.id==track.id}
                _isPrevAndNextTrackAvailable.emit(true)
            }
        } else {
            _loadingTrackStatus.emit(LoadStatus.Error(trackResult.exceptionOrNull()!!.message))
        }
    }

    suspend fun nextTrack(){
        val nextIndex = currentTrackIndex+1
        currentTrackIndex = if(nextIndex!=trackList.lastIndex) nextIndex else 0
        _currentTrack.emit(trackList[currentTrackIndex])
    }

    suspend fun prevTrack(){
        val prevIndex = currentTrackIndex-1
        currentTrackIndex = if(prevIndex>=0) prevIndex else trackList.lastIndex
        _currentTrack.emit(trackList[currentTrackIndex])
    }
}