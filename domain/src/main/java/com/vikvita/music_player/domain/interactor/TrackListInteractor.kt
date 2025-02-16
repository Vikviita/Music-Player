package com.vikvita.music_player.domain.interactor

import com.vikvita.music_player.domain.TrackRepository
import com.vikvita.music_player.domain.models.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * Интерактор для получения списка с api или c устройства,а так же поиск по имени
 * */
class TrackListInteractor(
    private val trackRepository: TrackRepository
){
    private val _updateTrackListStatus = MutableStateFlow<LoadStatus<List<Track>>>(LoadStatus.Initial)
    val updateTrackList = _updateTrackListStatus.asStateFlow()
/**
 * Загрузка изначального трека
 * */
    suspend fun initTrackList(){
       _updateTrackListStatus.emit(LoadStatus.InProgress)
        val result = trackRepository.getInitialList()
        if(result.isSuccess){
            val list = result.getOrNull()!!
            _updateTrackListStatus.emit(LoadStatus.Success(list))
        }
        else{
            _updateTrackListStatus.emit(LoadStatus.Error(result.exceptionOrNull()?.message))
        }
    }
    /**
     * Поиск трека по имени
     * */
    suspend fun searchByName(name:String){
        _updateTrackListStatus.emit(LoadStatus.InProgress)
        if(name.isBlank()){
            _updateTrackListStatus.emit(LoadStatus.Success(listOf()))
        }
        else{
            val result = trackRepository.findTrackByName(name)
            if(result.isSuccess){
                val list = result.getOrNull()!!
                _updateTrackListStatus.emit(LoadStatus.Success(list))
            }else{
                _updateTrackListStatus.emit(LoadStatus.Error(result.exceptionOrNull()?.message))
            }
        }
    }
}