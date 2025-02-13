package com.vikvita.music_player.domain.interactor

sealed interface LoadStatus{
    data object Initial: LoadStatus
    data object InProgress: LoadStatus
    data object Success: LoadStatus
    data class Error(val message:String?): LoadStatus
}