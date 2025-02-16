package com.vikvita.music_player.play_screen.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import com.vikvita.music_player.domain.interactor.LoadStatus
import com.vikvita.music_player.play_screen.PlayingTrackUiModelProvider
import com.vikvita.music_player.play_screen.components.CurrentTrackInfo
import com.vikvita.music_player.play_screen.components.PlayerControlBar
import com.vikvita.music_player.play_screen.models.PlayingTrackUiModel
import com.vikvita.music_player.play_screen.models.state
import com.vikvita.music_player.play_screen.rememberManagedMediaController
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.screens.BaseErrorStatusScreen
import com.vikvita.music_player.uikit.screens.BaseLoadingScreen
import com.vikvita.music_player.uikit.theme.Dimens

@Composable
fun PlayMusicScreen(
    initialId: String,
    viewModel: PlayMusicViewModel
) {
    val loadingStatus by viewModel.loadingStatus.collectAsStateWithLifecycle()
    val mediaController by rememberManagedMediaController()
    val currentTrackInfo=viewModel.currentTrack.collectAsStateWithLifecycle()
    val isNextTrackAvailable = remember { mutableStateOf(true) }
    val playerState = remember(key1 = mediaController){ mediaController?.state()  }
    LaunchedEffect(mediaController) {
        mediaController?.let {
            viewModel.loadTrackAndAlbumTracks(initialId)
        }

    }
    when (loadingStatus) {
        is LoadStatus.Error -> BaseErrorStatusScreen { viewModel.loadTrackAndAlbumTracks(initialId) }
        is LoadStatus.Success -> {
            DisposableEffect(Unit) {
                val data = (loadingStatus as LoadStatus.Success<List<MediaItem>>).data
                mediaController?.let { player ->
                    player.setMediaItems(data)
                    player.playWhenReady = true
                    player.prepare()
                }
                onDispose {
                        playerState?.dispose()

                }
            }
           LaunchedEffect(playerState?.mediaItemIndex){
               playerState?.run {
                   viewModel.getCurrentTrack(playerState.mediaItemIndex)
               }
           }
            PlayMusicScreenContent(
                track = currentTrackInfo,
                onProgressChange = {},
                onPauseClick = { mediaController?.pause() },
                onPrevClick = { mediaController?.seekToPrevious() },
                onNextClick = { mediaController?.seekToNext() },
                onResumceClick = { mediaController?.playWhenReady = true },
            )
        }

        else -> BaseLoadingScreen()
    }
}

@Composable
private fun PlayMusicScreenContent(
    track: State<PlayingTrackUiModel?>,
    onPauseClick: () -> Unit,
    onResumceClick: () -> Unit,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    onProgressChange: (Float) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        CurrentTrackInfo(
            modifier = Modifier
                .fillMaxWidth(),
            track = track.value
        )
        Spacer(Modifier.height(Dimens.Paddings.l))
        PlayerControlBar(
            onPauseClick = onPauseClick,
            onNextClick = onNextClick,
            onPrevClick = onPrevClick,
            onProgressChange = onProgressChange,
            onResumeClick = onResumceClick,
        )
    }
}

@Composable
@Preview
private fun PlayMusicScreenPreview(
    @PreviewParameter(PlayingTrackUiModelProvider::class) track: PlayingTrackUiModel
) {
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            PlayMusicScreenContent(
                track = remember { mutableStateOf(track) },
                onPauseClick = {},
                onNextClick = { },
                onPrevClick = {},
                onProgressChange = {},
                onResumceClick = {},
            )
        }
    }
}


