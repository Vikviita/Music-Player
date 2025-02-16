package com.vikvita.music_player.di

import com.vikvita.music_player.data.di.ApiRepository
import com.vikvita.music_player.data.di.LocalRepository
import com.vikvita.music_player.domain.TrackRepository
import com.vikvita.music_player.domain.interactor.PlayTrackInteractor
import com.vikvita.music_player.domain.interactor.TrackListInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton
/**
 * Квалифаер для Интерактора ,который работает с данными из api
 * */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiInteractor
/**
 * Квалифаер для Интерактора ,который работает с локальными данными
 * */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalInteractor

/**
 * Модуль для Предоставления зависимостей
 * */
@Module
class AppProvidingModule{
    @Singleton
    @Provides
    @ApiInteractor
    fun provideApiTrackListInteractor(@ApiRepository repository: TrackRepository):TrackListInteractor = TrackListInteractor(repository)

    @Singleton
    @Provides
    @LocalInteractor
    fun provideLocalTraclListInteractor(@LocalRepository repository: TrackRepository):TrackListInteractor = TrackListInteractor(repository)


    @Singleton
    @Provides
    @ApiInteractor
    fun provideApiPlayTrackInteractor(@ApiRepository repository: TrackRepository):PlayTrackInteractor = PlayTrackInteractor(repository)

    @Singleton
    @Provides
    @LocalInteractor
    fun provideLocalPlayTrackInteractor(@LocalRepository repository: TrackRepository):PlayTrackInteractor = PlayTrackInteractor(repository)
}