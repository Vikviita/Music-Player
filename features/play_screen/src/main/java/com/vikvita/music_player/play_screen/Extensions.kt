package com.vikvita.music_player.play_screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.session.MediaController
import com.vikvita.music_player.domain.models.Track
import com.vikvita.music_player.play_screen.models.MediaControllerManager
import com.vikvita.music_player.play_screen.models.PlayingTrackUiModel

internal fun Track.toPlayTrackUiModel(): PlayingTrackUiModel {
    return PlayingTrackUiModel(
        id = id,
        songTitle = trackTitle,
        albumTitle = albumName,
        artistName = artistName,
        picture = bigPicture,
        source = source
    )
}

internal fun List<Track>.toListMediaItem(): List<MediaItem> =
    map{ track ->
        MediaItem.Builder()
            .setMediaId(track.id)
            .setUri(track.source)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                    .setTitle(track.trackTitle)
                    .setArtist(track.artistName)
                    .setAlbumTitle(track.albumName)
                    .build()
            ).build()
    }

/**
 * Форматирует секунды в формат mm:ss
 * @param seconds количество секунд
 * */
internal fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val sec = seconds % 60
    return String.format("%02d:%02d", minutes, sec)
}

/**
 * Отдает в MediaController как State
 * */
@Composable
fun rememberManagedMediaController(
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
): State<MediaController?> {
    // Application context is used to prevent memory leaks
    val appContext = LocalContext.current.applicationContext
    val controllerManager = remember { MediaControllerManager.getInstance(appContext) }

    // Observe the lifecycle to initialize and release the MediaController at the appropriate times.
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> controllerManager.initialize()
                Lifecycle.Event.ON_STOP -> controllerManager.release()
                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    return controllerManager.controller
}