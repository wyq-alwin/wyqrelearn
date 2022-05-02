package com.example.wyqrelearn.imageloader

import android.graphics.Bitmap

class DoubleCache : ImageCache {
    val memoryCache = MemoryCache()
    val diskCache = DiskCache()

    override fun put(url: String, bitmap: Bitmap) {
        memoryCache.put(url, bitmap)
        diskCache.put(url, bitmap)
    }

    override fun get(url: String): Bitmap? {
        return memoryCache.get(url) ?: diskCache.get(url)
    }
}