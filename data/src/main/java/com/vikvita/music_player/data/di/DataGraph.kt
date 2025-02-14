package com.vikvita.music_player.data.di

import dagger.Module

@Module
internal class NetworkModule {}

@Module
internal interface DataRepositoryModule{}


@Module(includes = [NetworkModule::class,DataRepositoryModule::class])
interface DataGraph