package com.vikvita.music_player

import android.app.Application
import com.vikvita.music_player.data.di.DataRepositoryModule
import com.vikvita.music_player.data.di.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class,DataRepositoryModule::class])
interface ApplicationComponent {
}

class App:Application() {
    val appComponent = DaggerApplicationComponent.create()
}