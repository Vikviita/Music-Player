package com.vikvita.music_player.track_list.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vikvita.music_player.track_list.R
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.theme.Dimens

@Composable
internal fun StatusScreen(
    isProgressBarVisible: Boolean = false,
    message: String? = null,
    @DrawableRes icon: Int? = null
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            if (isProgressBarVisible) CircularProgressIndicator()
            icon?.let {
                Image(modifier = Modifier.size(100.dp),painter = painterResource(it), contentDescription = null)
                Spacer(modifier = Modifier.height(Dimens.Paddings.s))
            }
            message?.let {
                Text(it, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}


@Composable
@Preview
private fun LoadingStatusScreenPreview(){
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            StatusScreen(
                isProgressBarVisible = true,
                message = stringResource(R.string.downloading)
            )
        }
    }
}

@Composable
@Preview
private fun ImageStatusScreen(){
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            StatusScreen(
                icon = R.drawable.ic_search
            )
        }
    }
}

@Composable
@Preview
private fun ImageWithMessageStatusScreen(){
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            StatusScreen(
                icon = R.drawable.ic_search,
                message = stringResource(R.string.empty_list)
            )
        }
    }
}