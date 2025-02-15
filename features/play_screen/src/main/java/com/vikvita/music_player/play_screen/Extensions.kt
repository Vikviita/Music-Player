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
import androidx.media3.common.Player
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

internal fun List<Track>.toListPlayingTrackUiModel(): List<PlayingTrackUiModel> =
    map { it.toPlayTrackUiModel() }

internal val Player.currentMediaItems: List<MediaItem> get() {
    return List(mediaItemCount, ::getMediaItemAt)
}

fun Player.updatePlaylist(incoming: List<MediaItem>) {
    val oldMediaIds = currentMediaItems.map { it.mediaId }.toSet()
    val itemsToAdd = incoming.filterNot { item -> item.mediaId in oldMediaIds }
    addMediaItems(itemsToAdd)
}

fun Player.playMediaAt(index: Int) {
    if (currentMediaItemIndex == index)
        return
    seekToDefaultPosition(index)
    playWhenReady = true
    prepare()
}

internal fun PlayingTrackUiModel.toMediaItem(): MediaItem {
    return MediaItem.Builder()
        .setMediaId(id)
        .setUri(source)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                .setTitle(songTitle)
                .setArtist(artistName)
                .setAlbumTitle(albumTitle)
                .build()
        ).build()
}


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