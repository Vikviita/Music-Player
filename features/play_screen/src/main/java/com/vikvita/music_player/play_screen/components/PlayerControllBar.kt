package com.vikvita.music_player.play_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vikvita.music_player.play_screen.R
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.theme.Dimens


@Composable
fun PlayerControlBar(
    onPauseClick: () -> Unit,
    onResumeClick:() ->Unit,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    onProgressChange: (Float) -> Unit
) {
    val progress = remember{ mutableFloatStateOf(0f) }
    var isPause by remember { mutableStateOf(false) }
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.Paddings.s)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PlayerButton(
                    modifier = Modifier.size(50.dp),
                    icon = R.drawable.ic_previous,
                    onClick = {
                        onPrevClick()
                        isPause = false
                    }
                )
                PlayerButton(
                    modifier = Modifier.size(50.dp),
                    icon = if(isPause)R.drawable.ic_play else R.drawable.ic_pause ,
                    onClick = {
                        if(isPause) {
                            onResumeClick()
                            isPause = false

                        } else{
                            onPauseClick()
                            isPause=true
                        }
                    }
                )
                PlayerButton(
                    modifier = Modifier.size(50.dp),
                    icon = R.drawable.ic_next,
                    onClick = {
                        isPause = false
                        onNextClick()
                    }
                )
            }
            Spacer(modifier = Modifier.width(Dimens.Paddings.s))
            Slider(
                value = progress.floatValue,
                onValueChange = {progress.floatValue = it},
                onValueChangeFinished ={onProgressChange(progress.floatValue)},
                colors = SliderDefaults.colors(
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
            )
            Spacer(modifier = Modifier.width(Dimens.Paddings.s))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("00:00")
                Text("00:00")
            }
        }
    }


}


@Composable
@Preview
private fun PlayerControlBarPreview() {
    MusicPlayerTheme {
        PlayerControlBar(
            onProgressChange = {},
            onPauseClick = {},
            onNextClick = {},
            onPrevClick = {},
            onResumeClick ={},
        )
    }
}