package com.example.wyqrelearn.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.wyqrelearn.MainActivity
import com.example.wyqrelearn.model.GitHubService
import com.example.wyqrelearn.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(var service: GitHubService) : ViewModel() {

      fun testRetrofit() {

        val repos: Call<List<Repo>> = service.listRepos("octocat")
        repos.enqueue(object : Callback<List<Repo>?> {
            override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {
                Log.d(MainActivity.TAG, "Response:  ${response.body()?.map { it.name }}")
            }
        })
    }

    suspend fun f3() {
       viewModelScope.launch {  }
    }
}