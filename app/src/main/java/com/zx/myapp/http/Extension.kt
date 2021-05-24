package com.zx.myapp.http

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

inline fun HttpActivity.launchMain(
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

inline fun HttpActivity.launchIO(
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    return lifecycleScope.launch(
        Dispatchers.IO + GlobalCoroutineExceptionHandler(::handException)
    ) {
        block()
    }
}

inline fun HttpActivity.delayMain(
    delayTime: Long, crossinline block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch(GlobalCoroutineExceptionHandler(::handException)) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block()
    }
}
