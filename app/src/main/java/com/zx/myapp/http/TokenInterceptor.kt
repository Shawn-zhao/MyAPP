package com.zx.myapp.http

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

class TokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val oldRequest = chain.request()
        val requestBuilder = oldRequest.newBuilder()
            .header("Authorization", "Client-ID FoFDJ6OBS9vLBDzIv48ao51KocpQ1Hwf-GEqAWytdQY")
        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }
}