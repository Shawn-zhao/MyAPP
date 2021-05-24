package com.zx.myapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zx.myapp.http.GlobalCoroutineExceptionHandler
import com.zx.myapp.http.HttpInterface
import com.zx.myapp.http.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel : ViewModel() {
    private val httpInterface: HttpInterface = RetrofitFactory.httpInterface
    var searchPhotosResult = MutableLiveData<SearchPhotosResult>()
    fun getImages() {
        viewModelScope.launch(GlobalCoroutineExceptionHandler(::handException)) {
            val result = httpInterface.searchPhotos("China")
            searchPhotosResult.postValue(result)
        }
    }

    open fun handException(context: CoroutineContext, exception: Throwable) {
        var msg = ""
        if (exception is HttpException) {
            msg = when (exception.code()) {
                404 -> {
                    "$javaClass-异常了,请求404了，请求的资源不存在"
                }

                500 -> {
                    "$javaClass-异常了,请求500了，内部服务器错误"
                }
                401 -> {
                    "$javaClass-异常了,请求401了，身份认证不通过"
                }
                else -> {
                    "$javaClass-http请求异常了,${exception.response()}"
                }

            }
        } else {
            msg = "$javaClass-异常了,${exception.stackTraceToString()}"

        }
        Log.e("zx", msg)
    }
}