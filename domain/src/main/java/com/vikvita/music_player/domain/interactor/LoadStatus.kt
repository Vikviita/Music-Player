package com.vikvita.music_player.domain.interactor

sealed interface LoadStatus<out T>{
    data object Initial: LoadStatus<Nothing>
    data object InProgress: LoadStatus<Nothing>
    data class Success<T>(val data:T): LoadStatus<T>
    data class Error(val message:String?): LoadStatus<Nothing>
}