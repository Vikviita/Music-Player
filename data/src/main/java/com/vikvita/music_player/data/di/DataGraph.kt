package com.vikvita.music_player.data.di

import com.vikvita.music_player.data.api.ApiTrackRepository
import com.vikvita.music_player.data.api.NetworkService
import com.vikvita.music_player.data.api.RetrofitProvider
import com.vikvita.music_player.domain.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiRepository

@Module
internal class NetworkModule {
    @Singleton
    @Provides
    fun providerNetworkService(): NetworkService = RetrofitProvider().networkService
}

@Module
internal interface DataRepositoryModule{
    @Binds
    @Singleton
    @ApiRepository
    abstract fun bindTrackRepository(impl: ApiTrackRepository): TrackRepository
}

@Module(includes = [NetworkModule::class,DataRepositoryModule::class])
interface DataGraph
