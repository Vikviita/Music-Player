package com.vikvita.music_player.track_list.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.vikvita.music_player.track_list.R
import com.vikvita.music_player.track_list.TrackPreviewParametrProvider
import com.vikvita.music_player.track_list.models.TrackUiModel
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.theme.Dimens

@Composable
internal fun TrackListItem(modifier: Modifier=Modifier,track: TrackUiModel, onClick: (id: String) -> Unit) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick(track.id) }
        .padding(Dimens.Paddings.xs),
        verticalAlignment = Alignment.CenterVertically
        ) {
        Surface(shape = MaterialTheme.shapes.small){
            AsyncImage(
                modifier = Modifier.size(50.dp),
                model = track.cover,
                contentDescription = "Track cover",
                placeholder = painterResource(R.drawable.song_stub),
                error = painterResource(R.drawable.song_stub),
                fallback = painterResource(R.drawable.song_stub),
                onError = { Log.d("TAG",it.result.toString())}
            )
        }
        Spacer(modifier = Modifier.width(Dimens.Paddings.xs))
        Column {
            Text(track.title, style = MaterialTheme.typography.titleLarge)
            Text(track.author, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview
@Composable
private fun TrackListItemPreview(
    @PreviewParameter(TrackPreviewParametrProvider::class) track: TrackUiModel
) {
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TrackListItem(
                track = track
            ) {

            }
        }
    }
}