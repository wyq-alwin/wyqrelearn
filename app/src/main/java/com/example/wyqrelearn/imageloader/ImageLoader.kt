package com.example.wyqrelearn.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class ImageLoader(var imageCache: ImageCache) {

    private val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private val handler = Handler(Looper.getMainLooper())

    fun displayImage(url: String, imageView: ImageView) {
        imageCache.get(url)?.let {
            imageView.setImageBitmap(it)
            return
        }
        executeLoadRequest(url, imageView)
    }

    private fun executeLoadRequest(url: String, imageView: ImageView) {
        imageView.tag = url
        executor.execute {
            downloadImage(url)?.let {
                if (imageView.tag == url) {
                    updateImageView(imageView, it)
                }
                imageCache.put(url, it)
            }
        }
    }

    private fun updateImageView(imageView: ImageView, bitmap: Bitmap) {
        handler.post {
            imageView?.setImageBitmap(bitmap)
        }
    }

    fun downloadImage(imageUrl: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val conn = url.openConnection() as HttpURLConnection
            bitmap = BitmapFactory.decodeStream(conn.inputStream)
            conn.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }
}