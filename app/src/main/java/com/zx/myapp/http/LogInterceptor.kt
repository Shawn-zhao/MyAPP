package com.zx.myapp.http


import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by 赵想 on 2016/11/29.
 */

class LogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().toString()
        Log.i("zx", "发送请求 $url")
        val t1 = System.currentTimeMillis()
        val response = chain.proceed(request)
        val t2 = System.currentTimeMillis()
        Log.i("zx", "请求耗时" + (t2 - t1) + "毫秒,链接为 " + url)
        return response
    }

    companion object {
        val TAG = "zx"
    }
}
