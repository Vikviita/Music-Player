package com.vikvita.music_player.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppBindingModule {
    @Singleton
    @Binds
    fun provideApplicationContext(appInstance: Application): Context

}