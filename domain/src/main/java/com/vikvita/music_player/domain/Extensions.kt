package com.vikvita.music_player.domain

import com.vikvita.music_player.domain.interactor.LoadStatus

/**
 * Меняет элементы местами
 * @param index1 индекс первого элемента
 * @param index2 индекс второго элемента
 * */
internal fun <T> MutableList<T>.swap(index1:Int,index2:Int){
    if(index1==index2) return
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

/**
 * функция для изменения данных внутри [LoadStatus]
 * */
fun <T,R> LoadStatus<T>.map(transform:(T)->R): LoadStatus<R> {
    return when(this){
        is LoadStatus.Success -> LoadStatus.Success(transform(this.data))
        else->this as LoadStatus<R>
    }
}