package com.example.wyqrelearn

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.wyqrelearn.daohelp.UserDao
import com.example.wyqrelearn.databinding.ActivityMainBinding
import com.example.wyqrelearn.model.GitHubService
import com.example.wyqrelearn.model.Trunk
import com.example.wyqrelearn.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.R)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "wyqTest"

        @BindingAdapter("name")
        @JvmStatic
        fun loadImage(view: TextView?, name: String) {
            Log.e(TAG, "user name : $name")
        }
    }

    @Inject
    lateinit var trunk: Trunk

    @Inject
    lateinit var db: UserDao

    @Inject
    lateinit var service: GitHubService

    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val user1 = User1()
        binding.user1 = user1

        binding.btn1.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        binding.btn2.setOnClickListener {

            lifecycleScope.launch {
                flow {
                    for (i in 1..3) {
                        delay(100)
                        emit(i)
                    }
                }.collect { value -> Log.d("wyqTest1", "value :${value}") }
            }


        }
        binding.btn3.setOnClickListener {
            trunk.run()

            lifecycleScope.launchWhenCreated {
                viewModel.testRetrofit()
            }
        }
        viewModel
    }

    override fun onResume() {
        super.onResume()

//        binding.root.post {
//            startActivity(Intent(this, SecondActivity::class.java))
//        }
    }
}


class User1 : BaseObservable() {

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var age: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }
}



