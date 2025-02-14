package com.vikvita.music_player

import android.app.Application
import com.vikvita.music_player.data.di.DataGraph
import com.vikvita.music_player.di.AppBindingModule
import com.vikvita.music_player.di.AppProvidingModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataGraph::class,AppBindingModule::class,AppProvidingModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
    fun inject(mainActivity: MainActivity)
}

class App:Application() {
   lateinit var appComponent:ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent=DaggerApplicationComponent.builder().application(this).build()
    }
}