package com.vikvita.music_player.play_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.vikvita.music_player.play_screen.R
import com.vikvita.music_player.ui.theme.MusicPlayerTheme

@Composable
internal fun PlayerButton(
    modifier: Modifier=Modifier,
    @DrawableRes icon:Int,
    isEnabled:Boolean = true,
    onClick:()->Unit
){
    Surface(shape = MaterialTheme.shapes.extraLarge ){
        IconButton(onClick = onClick, enabled = isEnabled) {
            Icon(modifier = modifier, painter = painterResource(icon), contentDescription = null)
        }
    }
}

@Composable
@Preview
private fun PlayerButtonPreview(){
    MusicPlayerTheme {
        PlayerButton(
            icon = R.drawable.ic_next
        ) { }
    }
}