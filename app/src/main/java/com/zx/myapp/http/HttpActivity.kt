package com.zx.myapp.http

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

open class HttpActivity : AppCompatActivity() {
    val httpInterface: HttpInterface = RetrofitFactory.httpInterface
    private var progress: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    inline fun launchMain(
        crossinline block: suspend CoroutineScope.() -> Unit
    ) {
        val job = lifecycleScope.launch(GlobalCoroutineExceptionHandler(::handException)) {
            showProgress()
            block()
        }

        job.invokeOnCompletion {
            hideProgress()
        }
    }

    fun showProgress() {
        if (progress == null) {
            progress = ProgressDialog.show(
                this, "", "加载中,请稍后...", false, true
            )
        }
    }

    fun hideProgress() {
        if (progress != null && progress!!.isShowing) {
            progress!!.dismiss()
            progress = null
        }
    }

    open fun handException(context: CoroutineContext, exception: Throwable) {
        var msg = ""
        if (exception is HttpException) {
            msg = when (exception.code()) {
                404 -> {
                    "$localClassName-异常了,请求404了，请求的资源不存在"
                }

                500 -> {
                    "$localClassName-异常了,请求500了，内部服务器错误"
                }
                500 -> {
                    "$localClassName-异常了,请求401了，身份认证不通过"
                }
                else -> {
                    "$localClassName-http请求异常了,${exception.response()}"
                }

            }
        } else {
            msg = "$localClassName-异常了,${exception.stackTraceToString()}"

        }
        Log.e("zx", msg)

        hideProgress()
        Snackbar.make(
            window.decorView,
            msg,
            Snackbar.LENGTH_LONG
        )
            .show()
    }
}