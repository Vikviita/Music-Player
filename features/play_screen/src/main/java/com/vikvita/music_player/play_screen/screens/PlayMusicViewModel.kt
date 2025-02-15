package com.vikvita.music_player.play_screen.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vikvita.music_player.domain.interactor.PlayTrackInteractor
import com.vikvita.music_player.domain.interactor.map
import com.vikvita.music_player.play_screen.toPlayTrackUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

 class PlayMusicViewModel(
    private val playTrackInteractor: PlayTrackInteractor,
) : ViewModel() {
    val loadingStatus = playTrackInteractor.loadingTrackStatus.map { it.map{data->data.toPlayTrackUiModel()} }.stateIn(
        scope = viewModelScope,
        initialValue = null,
        started = SharingStarted.Lazily
    )
    val isNextTrackAvailable = playTrackInteractor.isPrevAndNextTrackAvailable
    fun getTrackById(id:String) = viewModelScope.launch {playTrackInteractor.getTrackById(id) }
    fun next() = viewModelScope.launch { playTrackInteractor.nextTrack() }
    fun prev()= viewModelScope.launch { playTrackInteractor.prevTrack() }
    companion object {
        fun factory( playTrackInteractor: PlayTrackInteractor) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PlayMusicViewModel(
                        playTrackInteractor = playTrackInteractor
                    ) as T
                }
            }
    }
}