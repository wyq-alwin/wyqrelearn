package com.example.wyqrelearn

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.net.Socket
import java.security.cert.X509Certificate
import javax.net.ssl.SSLEngine
import javax.net.ssl.X509ExtendedTrustManager


//管理Api的service
interface AppService {
    @GET
    fun getAppData(): Call<Any>
}

//service的⼯⼚类
object ServiceCreator {
    //构建retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    //创建定义好的service的实例
    inline fun <reified T> create(): T = create(T::class.java)
}
