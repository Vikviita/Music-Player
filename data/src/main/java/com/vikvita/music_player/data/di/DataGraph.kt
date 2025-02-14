package com.vikvita.music_player.data.di

import com.vikvita.music_player.data.local.TrackRepositoryLocal
import com.vikvita.music_player.domain.TrackRepository
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalRepository


@Module
internal class NetworkModule {}

@Module
internal interface DataRepositoryModule{
    @Binds
    @Singleton
    @LocalRepository
    fun bindTrackRepositoryLocal(impl:TrackRepositoryLocal):TrackRepository
}


@Module(includes = [NetworkModule::class,DataRepositoryModule::class])
interface DataGraph