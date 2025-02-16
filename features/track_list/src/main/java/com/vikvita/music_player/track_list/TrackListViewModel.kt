package com.vikvita.music_player.track_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vikvita.music_player.domain.interactor.LoadStatus
import com.vikvita.music_player.domain.interactor.TrackListInteractor
import com.vikvita.music_player.domain.map
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TrackListViewModel(
    private val trackListInteractor: TrackListInteractor
) : ViewModel() {
    init {
        initTrackList()
    }
    val loadingStatus = trackListInteractor.updateTrackList.map {status->
        status.map {list->
            list.toListTrackUiModel()
        }
    } .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = LoadStatus.Initial)

    fun initTrackList() = viewModelScope.launch { trackListInteractor.initTrackList() }
    fun searchTrackByName(name: String) =
        viewModelScope.launch { trackListInteractor.searchByName(name) }

    companion object {
        fun factory(trackListInteractor: TrackListInteractor): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                ): T {
                    return TrackListViewModel(
                        trackListInteractor = trackListInteractor
                    ) as T
                }
            }
    }

}