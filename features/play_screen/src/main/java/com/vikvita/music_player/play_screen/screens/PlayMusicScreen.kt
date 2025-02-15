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
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.vikvita.music_player.domain.interactor.PlayTrackInteractor
import com.vikvita.music_player.play_screen.PlayingTrackUiModelProvider
import com.vikvita.music_player.play_screen.components.CurrentTrackInfo
import com.vikvita.music_player.play_screen.components.PlayerControlBar
import com.vikvita.music_player.play_screen.models.PlayingTrackUiModel
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.theme.Dimens

@Composable
fun PlayMusicScreen(
   interactor: PlayTrackInteractor,
   viewModel: PlayMusicViewModel
) {

}

@Composable
private fun PlayMusicScreenContent(
     track:State<PlayingTrackUiModel>,
     onPauseClick:()->Unit,
     onNextClick:()->Unit,
     onPrevClick:()->Unit,
     onProgressChange:(Float)->Unit
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
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
            onProgressChange = onProgressChange
        )
    }
}

@Composable
@Preview
private fun PlayMusicScreenPreview(
    @PreviewParameter(PlayingTrackUiModelProvider::class) track:PlayingTrackUiModel
) {
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
           PlayMusicScreenContent(
               track = remember { mutableStateOf(track) },
               onPauseClick = {},
               onNextClick = { },
               onPrevClick = {},
               onProgressChange = {}
           )
        }
    }
}


