package com.vikvita.music_player.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton
@Module
abstract class AppBindingModule {
    @Singleton
    @Binds
    abstract fun context(appInstance: Application): Context

}