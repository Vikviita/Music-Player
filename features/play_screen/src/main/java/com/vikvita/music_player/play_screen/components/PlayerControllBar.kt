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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vikvita.music_player.play_screen.R
import com.vikvita.music_player.play_screen.formatTime
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.theme.Dimens


@Composable
fun PlayerControlBar(
    isNextAvailable: Boolean,
    isPause: Boolean,
    maxProgressPosition: State<Int>,
    currentProgressPosition: State<Int>,
    onPauseClick: () -> Unit,
    onResumeClick: () -> Unit,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    onProgressChange: (Int) -> Unit
) {
    val maxProgress =
        remember(maxProgressPosition.value) { maxProgressPosition.value.toFloat() }
    val maxProgresText =
        remember(maxProgress) { formatTime(maxProgressPosition.value) }
    val isMaxProgressNotNull by remember { derivedStateOf { maxProgressPosition.value != 0 } }
    var changingPosition by remember { mutableStateOf<Int?>(null) }
    val currentPositionText by remember(currentProgressPosition.value, changingPosition) {
        derivedStateOf {
            formatTime(changingPosition?:currentProgressPosition.value)
        }
    }
    val currentPositionFloat by remember(currentProgressPosition.value, changingPosition) {
        derivedStateOf {
            changingPosition?.toFloat()?:currentProgressPosition.value.toFloat()
        }
    }
    LaunchedEffect(currentProgressPosition.value){
        changingPosition = null
    }
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
                    }
                )
                PlayerButton(
                    modifier = Modifier.size(50.dp),
                    icon = if (isPause) R.drawable.ic_play else R.drawable.ic_pause,
                    onClick = {
                        if (isPause) {
                            onResumeClick()

                        } else {
                            onPauseClick()
                        }
                    }
                )
                PlayerButton(
                    modifier = Modifier.size(50.dp),
                    icon = R.drawable.ic_next,
                    isEnabled = isNextAvailable,
                    onClick = {
                        onNextClick()
                    }
                )
            }
            Spacer(modifier = Modifier.width(Dimens.Paddings.s))
            Slider(
                value = currentPositionFloat,
                onValueChange = { changingPosition = it.toInt() },
                onValueChangeFinished = {
                    changingPosition?.let { onProgressChange(it) }
                },
                valueRange = 0f..maxProgress,
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
                Text(currentPositionText)
                if (isMaxProgressNotNull) Text(maxProgresText)
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
            onResumeClick = {},
            isPause = true,
            isNextAvailable = true,
            maxProgressPosition = remember { mutableIntStateOf(0) },
            currentProgressPosition = remember { mutableIntStateOf(0) },
        )
    }
}