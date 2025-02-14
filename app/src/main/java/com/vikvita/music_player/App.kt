package com.vikvita.music_player

import android.app.Application
import com.vikvita.music_player.data.di.DataGraph
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataGraph::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}

class App:Application() {
    val appComponent = DaggerApplicationComponent.create()
}