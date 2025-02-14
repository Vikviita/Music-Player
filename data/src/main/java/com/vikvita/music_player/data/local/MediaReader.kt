package com.vikvita.music_player.data.local


import android.content.Context
import android.provider.MediaStore
import com.vikvita.music_player.data.local.models.LocalTrackModel
import javax.inject.Inject


internal class MediaReader @Inject constructor(
    private val context: Context
) {
    fun getAllAudioFromDevice(): List<LocalTrackModel> {
        val listOftrack = mutableListOf<LocalTrackModel>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.TITLE,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.ArtistColumns.ARTIST,
            MediaStore.Audio.AlbumColumns.ALBUM_ID
        )
        context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        ).use { cursor->
            if (cursor != null) {
                val pathColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA)
                val nameColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
                val albumColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)
                val authorColumns = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
                while (cursor.moveToNext()) {
                    val path = runCatching { cursor.getString(pathColumn) }.getOrNull()
                    val name = runCatching { cursor.getString(nameColumn) }.getOrNull()
                    val album = runCatching { cursor.getString(albumColumn) }.getOrNull()
                    val artist = runCatching { cursor.getString(authorColumns) }.getOrNull()
                    path?.let {
                        listOftrack.add(
                            LocalTrackModel(
                                title = name ?: "<Unknown>",
                                albumName = album ?: "<Unknown>",
                                authorName = artist ?: "<Unknown author>",
                                cover = null,
                                path = path
                            )
                        )
                    }



                }
            }

        }

        return listOftrack.toList()
    }
}