package com.vikvita.music_player.domain.interactor

import com.vikvita.music_player.domain.TrackRepository
import com.vikvita.music_player.domain.models.Track
import com.vikvita.music_player.domain.swap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * Интерактор для получения списка треков,которые будут проиграны
 * */
class PlayTrackInteractor(
    private val trackRepository: TrackRepository
) {
    private val _loadingTrackStatus = MutableStateFlow<LoadStatus<List<Track>>>(LoadStatus.Initial)
    private val _currentTrack = MutableStateFlow<Track?>(null)
    val currentTrack = _currentTrack.asStateFlow()
    private var listOfTrack: MutableList<Track> = mutableListOf()
    val loadingTrackStatus = _loadingTrackStatus.asStateFlow()
   /**
    * Загрузка трека по id,а так же остальных треков из этого же альбома.
    * Искомый трек будет в начале списка
    * */
    suspend fun loadTrackAndAlbumTracks(trackId: String) {
        if(listOfTrack.isNotEmpty()) return
        _loadingTrackStatus.emit(LoadStatus.InProgress)
        val trackResult = trackRepository.getTrackById(trackId)
        if (trackResult.isSuccess) {
            val track = trackResult.getOrNull()!!
            val trackListByAlbum = trackRepository.getTracksByAlbum(track.albumId)
            if (trackListByAlbum.isSuccess) {
                listOfTrack.addAll(trackListByAlbum.getOrNull()!!)
                val trackIndexById = listOfTrack.indexOfFirst { it.id == track.id }
                listOfTrack.swap(trackIndexById, 0)
                _loadingTrackStatus.emit(LoadStatus.Success(listOfTrack.toList()))
            }
        } else {
            _loadingTrackStatus.emit(LoadStatus.Error(trackResult.exceptionOrNull()!!.message))
        }
    }
/**
 * Очистка данных после выхода с экрана проигрывания
 * */
    fun clear() {
        listOfTrack.clear()
        _currentTrack.value = null
        _loadingTrackStatus.value = LoadStatus.Initial
    }
/**
 * Получение данных определенного трека из списка по индексу
 * */
    suspend fun getTrackByIndex(index: Int) {
        val track = kotlin.runCatching { listOfTrack[index] }.getOrNull()
        _currentTrack.emit(track)
    }
}