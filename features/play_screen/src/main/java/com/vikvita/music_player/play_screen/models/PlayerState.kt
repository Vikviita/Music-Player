package com.vikvita.music_player.play_screen.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.media3.common.Player

/**
 * Create a instance of [PlayerState] and register a [listener][Player.Listener] to the [Player] to
 * observe its states.
 *
 * NOTE: Should call [dispose][PlayerState.dispose] to unregister the listener to avoid leaking this
 * instance when it is no longer used.
 */
internal fun Player.state(): PlayerState {
    return PlayerStateImpl(this)
}

/**
 * A state object that can be used to observe the [player]'s states.
 */
internal interface PlayerState {
    val player: Player

    val mediaItemIndex: Int


    fun dispose()
}

internal class PlayerStateImpl(
    override val player: Player
) : PlayerState {
    override var mediaItemIndex: Int by mutableIntStateOf(player.currentMediaItemIndex)
        private set


    private val listener = object : Player.Listener {
        override fun onPositionDiscontinuity(
            oldPosition: Player.PositionInfo,
            newPosition: Player.PositionInfo,
            reason: Int
        ) {
            mediaItemIndex = player.currentMediaItemIndex
        }
    }

    init {
        player.addListener(listener)
    }

    override fun dispose() {
        player.removeListener(listener)
    }
}