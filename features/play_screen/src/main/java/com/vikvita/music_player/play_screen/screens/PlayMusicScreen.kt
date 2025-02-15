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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vikvita.music_player.domain.interactor.LoadStatus
import com.vikvita.music_player.play_screen.PlayingTrackUiModelProvider
import com.vikvita.music_player.play_screen.components.CurrentTrackInfo
import com.vikvita.music_player.play_screen.components.PlayerControlBar
import com.vikvita.music_player.play_screen.models.PlayingTrackUiModel
import com.vikvita.music_player.play_screen.rememberManagedMediaController
import com.vikvita.music_player.play_screen.toMediaItem
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
    val isNextTrackAvailable = viewModel.isNextTrackAvailable.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getTrackById(initialId)
    }
    when (loadingStatus) {
        is LoadStatus.Error -> BaseErrorStatusScreen { viewModel.getTrackById(initialId) }
        is LoadStatus.Success -> {
            PlayMusicScreenContent(
                track = (loadingStatus as LoadStatus.Success<PlayingTrackUiModel>).data,
                onProgressChange = {},
                onPauseClick = { mediaController?.pause() },
                onPrevClick = viewModel::prev,
                onNextClick = viewModel::next,
                prepareTrack = {
                    mediaController?.let { player ->
                        player.setMediaItem(it.toMediaItem())
                        player.playWhenReady = true
                        player.prepare()
                    }
                },
                onResumceClick = { mediaController?.playWhenReady = true },
                isNextTrackAvailable = isNextTrackAvailable
            )
        }

        else -> BaseLoadingScreen()
    }
}

@Composable
private fun PlayMusicScreenContent(
    track: PlayingTrackUiModel,
    onPauseClick: () -> Unit,
    onResumceClick: () -> Unit,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    prepareTrack: (track: PlayingTrackUiModel) -> Unit,
    onProgressChange: (Float) -> Unit,
    isNextTrackAvailable:State<Boolean>
) {
    LaunchedEffect(track) {
        prepareTrack(track)
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        CurrentTrackInfo(
            modifier = Modifier
                .fillMaxWidth(),
            track = track
        )
        Spacer(Modifier.height(Dimens.Paddings.l))
        PlayerControlBar(
            onPauseClick = onPauseClick,
            onNextClick = onNextClick,
            onPrevClick = onPrevClick,
            onProgressChange = onProgressChange,
            onResumeClick = onResumceClick,
            isNextTrackAvailable = isNextTrackAvailable
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
                track = track,
                onPauseClick = {},
                onNextClick = { },
                onPrevClick = {},
                onProgressChange = {},
                onResumceClick = {},
                prepareTrack = {},
                isNextTrackAvailable = remember { mutableStateOf(true) }
            )
        }
    }
}


