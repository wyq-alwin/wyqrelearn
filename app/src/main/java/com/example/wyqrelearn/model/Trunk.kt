package com.example.wyqrelearn.model

import android.util.Log
import javax.inject.Inject

class Trunk @Inject constructor(val driver: Driver) {
    fun run() {
        Log.d("wyqTest", "Trunk run: ${driver.hashCode()}")
    }
}