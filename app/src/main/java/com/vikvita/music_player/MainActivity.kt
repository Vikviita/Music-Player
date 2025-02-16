package com.vikvita.music_player

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.vikvita.music_player.di.ApiInteractor
import com.vikvita.music_player.di.LocalInteractor
import com.vikvita.music_player.domain.interactor.PlayTrackInteractor
import com.vikvita.music_player.domain.interactor.TrackListInteractor
import com.vikvita.music_player.ui.theme.MusicPlayerTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    @ApiInteractor
    lateinit var apiInteractor: TrackListInteractor

    @Inject
    @LocalInteractor
    lateinit var localInteractor: TrackListInteractor

    @Inject
    @LocalInteractor
    lateinit var localPlayInteractor:PlayTrackInteractor

    @Inject
    @ApiInteractor
    lateinit var apiPlayInteractor:PlayTrackInteractor

    private val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MusicPlayerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainNavHost(
                        navController = navController,
                        apiTrackInteractor = apiInteractor,
                        localInteractor = localInteractor,
                        localPlayInteractor = localPlayInteractor,
                        apiPlayTrackInteractor = apiPlayInteractor
                    )
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        if (packageManager.checkPermission(
                permission,
                packageName
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(permission), 0)
        }
    }
}


