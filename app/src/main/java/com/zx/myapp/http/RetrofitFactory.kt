package com.zx.myapp.http

import android.util.Log
import com.zx.myapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory
{
    val httpInterface: HttpInterface;
    init {
        val builder = OkHttpClient().newBuilder()
        builder.retryOnConnectionFailure(true) //连接失败后是否重新连接
                .connectTimeout(15, TimeUnit.SECONDS) //超时时间15S

        builder.addInterceptor(TokenInterceptor())
        if (BuildConfig.DEBUG) {
            Log.i("zx", "添加了日志拦截器")
            builder.addInterceptor(LogInterceptor()) //日志拦截器
        }
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()
        httpInterface = retrofit.create(HttpInterface::class.java)
    }
}