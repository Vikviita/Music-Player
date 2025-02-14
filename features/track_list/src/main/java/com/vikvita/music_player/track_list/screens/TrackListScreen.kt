package com.vikvita.music_player.track_list.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vikvita.music_player.domain.interactor.LoadStatus
import com.vikvita.music_player.track_list.R
import com.vikvita.music_player.track_list.TrackListPreviewParametrProvider
import com.vikvita.music_player.track_list.TrackListViewModel
import com.vikvita.music_player.track_list.components.TrackListItem
import com.vikvita.music_player.track_list.components.TrackSearchBar
import com.vikvita.music_player.track_list.models.ActionButtonParams
import com.vikvita.music_player.track_list.models.TrackUiModel
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import com.vikvita.music_player.uikit.theme.Dimens

@Composable
fun TrackListScreen(
    viewModel: TrackListViewModel,
    goToPlay: (String) -> Unit
) {
    val trackList = viewModel.trackList.collectAsStateWithLifecycle()
    val status = viewModel.loadingStatus.collectAsStateWithLifecycle()
    TrackListScreenContent(
        trackList = trackList,
        search = viewModel::searchTrackByName,
        clear = viewModel::initTrackList,
        goToPlay = goToPlay,
        status = status,
        restart = viewModel::initTrackList
    )
}


@Composable
private fun TrackListScreenContent(
    trackList: State<List<TrackUiModel>>,
    status: State<LoadStatus>,
    search: (String) -> Unit,
    clear: () -> Unit,
    goToPlay: (String) -> Unit,
    restart:()->Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TrackSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.Paddings.xs),
            startSearch = search,
            clear = clear
        )

        when (val status = status.value) {
            is LoadStatus.Error -> {
                StatusScreen(
                    icon = R.drawable.ic_error,
                    message = stringResource(R.string.something_wrong),
                    action = ActionButtonParams(
                       text = stringResource(R.string.restart),
                        onClick = restart
                    )
                )
            }
            LoadStatus.Initial, LoadStatus.InProgress -> {
                StatusScreen(
                    isProgressBarVisible = true,
                    message = stringResource(R.string.downloading)
                )
            }
            LoadStatus.Success -> {
                if (trackList.value.isNotEmpty()) {
                    LazyColumn {
                        items(
                            items = trackList.value,
                            key = { it.id }
                        ) {
                            TrackListItem(track = it, onClick = goToPlay)
                            Spacer(Modifier.height(Dimens.Paddings.xxs))
                        }
                    }
                } else {
                    StatusScreen(
                        icon = R.drawable.ic_search_off,
                        message = stringResource(R.string.empty_list)
                    )
                }

            }
        }

    }


}


@Composable
@Preview
private fun TrackListScreenPreview(
    @PreviewParameter(TrackListPreviewParametrProvider::class) list: List<TrackUiModel>
) {
    MusicPlayerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TrackListScreenContent(
                trackList = remember { mutableStateOf(listOf()) },
                status = remember { mutableStateOf(LoadStatus.Success) },
                search = {},
                clear = {},
                goToPlay = {}
            ) { }
        }
    }

}