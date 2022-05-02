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
import com.example.wyqrelearn.imageloader.DiskCache
import com.example.wyqrelearn.imageloader.DoubleCache
import com.example.wyqrelearn.imageloader.ImageLoader
import com.example.wyqrelearn.imageloader.MemoryCache
import com.example.wyqrelearn.model.GitHubService
import com.example.wyqrelearn.model.Repo
import com.example.wyqrelearn.model.Trunk
import com.example.wyqrelearn.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
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
    lateinit var imageLoader: ImageLoader

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
                val call = service.listRepos("rengwuxian")
                call.enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(
                        call: Call<List<Repo>>,
                        response: Response<List<Repo>>
                    ) {
                        Log.d(TAG, "listRepos ${response.body()}")
                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                        Log.d(TAG, "listRepos fail")
                    }

                })
            }
        }
        binding.btn3.setOnClickListener {
            val client = OkHttpClient()
            val url = "https://api.github.com/users/rengwuxian/repos"
            val request = Request.Builder()
                .url(url)
                .build()
            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.d(TAG, "listRepos fail")
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    Log.d(TAG, "listRepos ${response.body}")
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        val url =
            "https://t14.baidu.com/it/u=3871151578,586465891&fm=179&app=42&size=w621&n=0&f=PNG?s=56F72C72CCB47E904B7DA3C40300A026&sec=1650992400&t=967cbe4f0f89aaab15c34899f4006df7"
        imageLoader = ImageLoader(MemoryCache())
        imageLoader.displayImage(url, binding.image)

        lifecycleScope.launch {
            delay(1000)
            imageLoader.imageCache = DiskCache()
            imageLoader.displayImage(url, binding.image)
        }

        lifecycleScope.launch {
            delay(2000)
            imageLoader.imageCache = DoubleCache()
            imageLoader.displayImage(url, binding.image)
        }

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
