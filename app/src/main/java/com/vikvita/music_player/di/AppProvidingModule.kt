package com.vikvita.music_player.di

import dagger.Module
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiTrackInteractor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalTrackInteractor


@Module
class AppProvidingModule{
    
}