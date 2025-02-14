package com.vikvita.music_player.track_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.vikvita.music_player.track_list.R
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.theme.Dimens

@Composable
internal fun TrackSearchBar(
    modifier: Modifier = Modifier,
    startSearch: (String) -> Unit,
    clear: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    val filterText = remember { mutableStateOf<String?>(null) }
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            placeholder = {Text(stringResource(R.string.search_bar_hint))},
            onValueChange = {
                searchText = it
            },
            leadingIcon = {
                Icon(painter = painterResource(R.drawable.ic_search), contentDescription = null)
            },
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (searchText.isNotBlank()) {
                        startSearch(searchText)
                        filterText.value = searchText
                        searchText=""
                        focusManager.clearFocus()
                    }

                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            )
        )
        Spacer(modifier = Modifier.height(Dimens.Paddings.xs))
        SearchFilterChip(
            text = filterText,
        ) {
            clear()
            filterText.value = null
        }
    }

}

@Preview
@Composable
private fun SearchBarPreview() {
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TrackSearchBar(
                startSearch = {},
                clear = {}
            )
        }
    }
}