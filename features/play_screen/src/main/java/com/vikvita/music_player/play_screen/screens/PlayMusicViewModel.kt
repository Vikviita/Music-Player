package com.vikvita.music_player.play_screen.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vikvita.music_player.domain.interactor.PlayTrackInteractor
import com.vikvita.music_player.domain.map
import com.vikvita.music_player.play_screen.toListMediaItem
import com.vikvita.music_player.play_screen.toPlayTrackUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlayMusicViewModel(
    private val playTrackInteractor: PlayTrackInteractor,
) : ViewModel() {
    val loadingStatus =
        playTrackInteractor.loadingTrackStatus.map {
            it.map { data ->
                data.toListMediaItem()
            }
        }
            .stateIn(
                scope = viewModelScope,
                initialValue = null,
                started = SharingStarted.Lazily
            )

    val currentTrack = playTrackInteractor.currentTrack.map { it?.toPlayTrackUiModel() }.stateIn(
        scope = viewModelScope,
        initialValue = null,
        started = SharingStarted.Lazily
    )

    fun loadTrackAndAlbumTracks(id: String) =
        viewModelScope.launch { playTrackInteractor.loadTrackAndAlbumTracks(id) }

    fun getCurrentTrack(index: Int) =
        viewModelScope.launch { playTrackInteractor.getTrackByIndex(index) }

    override fun onCleared() {
        playTrackInteractor.clear()
        super.onCleared()
    }

    companion object {
        fun factory(playTrackInteractor: PlayTrackInteractor) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PlayMusicViewModel(
                        playTrackInteractor = playTrackInteractor
                    ) as T
                }
            }
    }
}