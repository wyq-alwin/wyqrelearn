package com.example.wyqrelearn.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileOutputStream

class DiskCache : ImageCache {
    companion object {
        val cacheDir = "sdcard/cache/"
    }

    override fun put(url: String, bitmap: Bitmap) {
        try {
            FileOutputStream(cacheDir + url).use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
        } catch (e: Exception) {
        }

    }

    override fun get(url: String): Bitmap? {
        return BitmapFactory.decodeFile(cacheDir + url)
    }
}