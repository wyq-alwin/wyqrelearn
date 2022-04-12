package com.example.wyqrelearn

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.wyqrelearn.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.image.postDelayed({
//            binding.image.useFloatingLabel = false
//        }, 2000)
//        binding.image.postDelayed({
//            binding.image.useFloatingLabel = true
//        }, 4000)
    }

    override fun onResume() {
        super.onResume()
    }
}