package com.example.wyqrelearn.imageloader

import android.graphics.Bitmap
import androidx.collection.LruCache
import androidx.collection.lruCache

class MemoryCache : ImageCache {
    private lateinit var cache: LruCache<String, Bitmap>

    init {
        initImageCache()
    }

    private fun initImageCache() {
        val maxMemory = Runtime.getRuntime().maxMemory().toInt() / 1024
        val cacheSize = maxMemory / 4
        cache = lruCache(cacheSize, { key, bitmap ->
            bitmap.rowBytes * bitmap.height / 1024
        })
    }

    override fun put(url: String, bitmap: Bitmap) {
        cache.put(url, bitmap)
    }

    override fun get(url: String): Bitmap? {
        return cache.get(url)
    }
}