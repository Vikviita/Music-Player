package com.vikvita.music_player

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.vikvita.music_player.domain.interactor.TrackListInteractor
import com.vikvita.music_player.track_list.TrackListViewModel
import com.vikvita.music_player.track_list.screens.TrackListScreen
import kotlinx.serialization.Serializable

@Composable
fun MainNavHost(
    navController: NavHostController,
    localInteractor:TrackListInteractor,
    apiTrackInteractor: TrackListInteractor
) {
    val routes = remember {
        listOf(
            (R.drawable.ic_api_music to MainNavRoute.ApiTrackList),
            (R.drawable.ic_local_music to MainNavRoute.LocalTrackList)
        )
    }
    val isNavBarVisible = remember { mutableStateOf(true) }
    Scaffold(bottomBar = {
        if (isNavBarVisible.value) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            NavigationBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                routes.forEach { (resIcon, topLevelRoute) ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.hasRoute(
                                topLevelRoute::class
                            )
                        } == true,
                        icon = {
                            Icon(
                                painterResource(resIcon),
                                contentDescription = null
                            )
                        },
                        onClick = {
                            navController.navigate(topLevelRoute) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }

    }){ padding->
        NavHost(
            modifier = Modifier
                .padding(padding),
            navController = navController,
            startDestination = MainNavRoute.ApiTrackList
        ) {
            composable<MainNavRoute.ApiTrackList> {
                isNavBarVisible.value = true
                val viewModel: TrackListViewModel = viewModel(
                    factory = TrackListViewModel.factory(trackListInteractor = apiTrackInteractor)
                )
                TrackListScreen(
                    viewModel = viewModel
                ) { id ->
                    navController.navigate(MainNavRoute.PlayMusicScreen(id))
                }
            }
            composable<MainNavRoute.LocalTrackList> {
                isNavBarVisible.value = true
                val viewModel: TrackListViewModel = viewModel(
                    factory = TrackListViewModel.factory(trackListInteractor = localInteractor)
                )
                TrackListScreen(
                    viewModel = viewModel
                ) { id ->
                    navController.navigate(MainNavRoute.PlayMusicScreen(id))
                }
            }
            composable<MainNavRoute.PlayMusicScreen> { backStackEntry ->
                isNavBarVisible.value = false
                val playMusic: MainNavRoute.PlayMusicScreen =
                    backStackEntry.toRoute()
            }
        }

    }
}

@Serializable
private sealed interface MainNavRoute {
    @Serializable
    data object ApiTrackList

    @Serializable
    data object LocalTrackList

    @Serializable
    data class PlayMusicScreen(val id: String)
}