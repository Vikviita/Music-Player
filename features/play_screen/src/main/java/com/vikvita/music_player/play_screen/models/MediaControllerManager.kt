package com.vikvita.music_player.play_screen.models

import android.content.ComponentName
import android.content.Context
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.vikvita.music_player.play_screen.MusicService

@Stable
internal class MediaControllerManager private constructor(context: Context) : RememberObserver {
    private val appContext = context.applicationContext
    private var factory: ListenableFuture<MediaController>? = null
    var controller = mutableStateOf<MediaController?>(null)
        private set

    init {
        initialize()
    }

    /**
     * Initializes the MediaController.
     *
     * If the MediaController has not been built or has been released, this method will build a new one.
     */
    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    internal fun initialize() {
        if (factory == null || factory?.isDone == true) {
            factory = MediaController.Builder(
                appContext,
                SessionToken(appContext, ComponentName(appContext, MusicService::class.java))
            ).buildAsync()
        }
        factory?.addListener(
            {
                // MediaController is available here with controllerFuture.get()
                controller.value = factory?.let {
                    if (it.isDone)
                        it.get()
                    else
                        null
                }
            },
            MoreExecutors.directExecutor()
        )
    }

    /**
     * Releases the MediaController.
     *
     * This method will release the MediaController and set the controller state to null.
     */
    internal fun release() {
        factory?.let {
            MediaController.releaseFuture(it)
            controller.value = null
        }
        factory = null
    }

    // Lifecycle methods for the RememberObserver interface.
    override fun onAbandoned() {
        release()
    }

    override fun onForgotten() {
        release()
    }

    override fun onRemembered() {}

    companion object {
        @Volatile
        private var instance: MediaControllerManager? = null

        /**
         * Returns the Singleton instance of the MediaControllerManager.
         *
         * @param context The context to use when creating the MediaControllerManager.
         * @return The Singleton instance of the MediaControllerManager.
         */
        fun getInstance(context: Context): MediaControllerManager {
            return instance ?: synchronized(this) {
                instance ?: MediaControllerManager(context).also { instance = it }
            }
        }
    }
}