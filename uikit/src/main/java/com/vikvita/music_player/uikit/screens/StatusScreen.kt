package com.vikvita.music_player.uikit.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.R
import com.vikvita.music_player.uikit.theme.Dimens

@Composable
fun StatusScreen(
    isProgressBarVisible: Boolean = false,
    message: String? = null,
    @DrawableRes icon: Int? = null,
    action: ActionButtonParams? = null
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (isProgressBarVisible) CircularProgressIndicator()
            icon?.let {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(it),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(Dimens.Paddings.s))
            }
            message?.let {
                Text(it, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(Dimens.Paddings.s))
            }
            action?.let { param ->
                Button(onClick = param.onClick) {
                    Text(text = param.text)
                }
            }

        }
    }
}

@Composable
fun BaseErrorStatusScreen(onRestart: () -> Unit) {
    StatusScreen(
        message = stringResource(R.string.something_wrong),
        icon = R.drawable.ic_error,
        action = ActionButtonParams(
            text = stringResource(R.string.restart),
            onClick = onRestart
        )
    )
}

@Composable
fun BaseLoadingScreen(){
    StatusScreen(
        isProgressBarVisible = true,
        message = stringResource(R.string.downloading),
    )
}


@Composable
@Preview
private fun LoadingStatusScreenPreview() {
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
           BaseLoadingScreen()
        }
    }
}

@Composable
@Preview
private fun ErrorStatusScreenPreview() {
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
           BaseErrorStatusScreen {  }
        }
    }
}