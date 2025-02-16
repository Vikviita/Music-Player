package com.vikvita.music_player.play_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vikvita.music_player.play_screen.PlayingTrackUiModelProvider
import com.vikvita.music_player.play_screen.R
import com.vikvita.music_player.play_screen.models.PlayingTrackUiModel
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.theme.Dimens
@Composable
internal fun CurrentTrackInfo(
    modifier: Modifier = Modifier,
    track: PlayingTrackUiModel?
) {
    Column(
        modifier = modifier.padding(start = Dimens.Paddings.l),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(shape = MaterialTheme.shapes.large) {
            AsyncImage(
                modifier = Modifier.size(350.dp),
                model = track?.picture,
                contentDescription = null,
                placeholder = painterResource(R.drawable.song_stub),
                error = painterResource(R.drawable.song_stub),
                fallback = painterResource(R.drawable.song_stub),
            )
        }
        track?.let {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    track.songTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    track.albumTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    track.artistName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

    }

}


@Composable
@Preview
private fun CurrentTrackInfoPreview(
    @PreviewParameter(PlayingTrackUiModelProvider::class) track: PlayingTrackUiModel
) {
    MusicPlayerTheme {
        Surface {
            CurrentTrackInfo(
                track = track
            )
        }

    }
}