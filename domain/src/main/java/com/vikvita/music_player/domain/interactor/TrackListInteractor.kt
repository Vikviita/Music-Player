package com.vikvita.music_player.domain.interactor

import com.vikvita.music_player.domain.TrackRepository
import com.vikvita.music_player.domain.models.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrackListInteractor(
    private val trackRepository: TrackRepository
){
    private val _listOfTrack = MutableStateFlow<List<Track>>(listOf())
    private val _updateTrackListStatus = MutableStateFlow<LoadStatus>(LoadStatus.Initial)
    val listOfTrack = _listOfTrack.asStateFlow()
    val updateTrackList = _updateTrackListStatus.asStateFlow()

    suspend fun initTrackList(){
       _updateTrackListStatus.emit(LoadStatus.InProgress)
        val result = trackRepository.getInitialList()
        if(result.isSuccess){
            val list = result.getOrNull()!!
            _listOfTrack.emit(list)
            _updateTrackListStatus.emit(LoadStatus.Success)
        }
        else{
            _updateTrackListStatus.emit(LoadStatus.Error(result.exceptionOrNull()?.message))
        }
    }
    suspend fun searchByName(name:String){
        _updateTrackListStatus.emit(LoadStatus.InProgress)
        if(name.isBlank()){
            _updateTrackListStatus.emit(LoadStatus.Success)
            _listOfTrack.emit(listOf())
        }
        else{
            val result = trackRepository.findTrackByName(name)
            if(result.isSuccess){
                val list = result.getOrNull()!!
                _listOfTrack.emit(list)
                _updateTrackListStatus.emit(LoadStatus.Success)
            }else{
                _updateTrackListStatus.emit(LoadStatus.Error(result.exceptionOrNull()?.message))
            }
        }
    }
}