package com.vikvita.music_player.di

import com.vikvita.music_player.data.di.ApiRepository
import com.vikvita.music_player.data.di.LocalRepository
import com.vikvita.music_player.domain.TrackRepository
import com.vikvita.music_player.domain.interactor.TrackListInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiTrackInteractor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalTrackInteractor


@Module
class AppProvidingModule{
    @Singleton
    @Provides
    @ApiTrackInteractor
    fun provideApiTrackListInteractor(@ApiRepository repository: TrackRepository):TrackListInteractor = TrackListInteractor(repository)

    @Singleton
    @Provides
    @LocalTrackInteractor
    fun provideLocalTraclListInteractor(@LocalRepository repository: TrackRepository):TrackListInteractor = TrackListInteractor(repository)
}