package com.vikvita.music_player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        val routes = listOf(
            (R.drawable.ic_api_music to MainNavRoute.ApiTrackList),
            (R.drawable.ic_local_music to MainNavRoute.LocalTrackList)
        )
        setContent {
            val isNavBarVisible = remember { mutableStateOf(true) }
            val navController = rememberNavController()
            MusicPlayerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
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

                    }) {
                        NavHost(
                            modifier = Modifier
                                .padding(it),
                            navController = navController,
                            startDestination = MainNavRoute.ApiTrackList
                        ) {
                            composable<MainNavRoute.ApiTrackList> {
                                isNavBarVisible.value = true
                            }
                            composable<MainNavRoute.LocalTrackList> {
                                isNavBarVisible.value = true
                            }
                            composable<MainNavRoute.PlayMusicScreen> { backStackEntry ->
                                isNavBarVisible.value = false
                                val playMusic: MainNavRoute.PlayMusicScreen =
                                    backStackEntry.toRoute()
                            }
                        }
                    }

                }
            }


        }
    }
}

@Serializable
sealed interface MainNavRoute {
    @Serializable
    data object ApiTrackList

    @Serializable
    data object LocalTrackList

    @Serializable
    data class PlayMusicScreen(val id: String)
}
