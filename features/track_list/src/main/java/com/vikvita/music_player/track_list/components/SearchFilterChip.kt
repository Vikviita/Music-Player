package com.vikvita.music_player.track_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.vikvita.music_player.track_list.R
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.theme.Dimens

@Composable
internal fun SearchFilterChip(
    modifier: Modifier = Modifier,
    text: State<String?>,
    onCrossClick: () -> Unit
) {
    AnimatedVisibility(text.value != null) {
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = MaterialTheme.shapes.small
        ) {
            Row(
                modifier = modifier.padding(start = Dimens.Paddings.xxs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = text.value ?: "")
                IconButton(onClick = onCrossClick) {
                    Icon(painter = painterResource(R.drawable.ic_clear), contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(Dimens.Paddings.xs))
        }
    }

}

@Composable
@Preview
private fun SearchFilterChipPreview() {
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SearchFilterChip(text = remember { mutableStateOf("Adele") }) { }
        }
    }
}