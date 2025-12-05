package com.espiralsoft.filem.utils

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever

object VideoThumbnailGenerator {

    fun generate(path: String): Bitmap? {
        return try {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(path)
            val frame = retriever.getFrameAtTime(0)
            retriever.release()
            frame
        } catch (_: Exception) {
            null
        }
    }
}
