package com.zx.myapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

open class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    val viewBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        getViewBinding(layoutInflater)
    }

    private fun <VB : ViewBinding> getViewBinding(inflater: LayoutInflater, position: Int = 0): VB {
        Log.i("zx", "javaClass=" + javaClass.toString())
        Log.i("zx", "javaClass.genericSuperclass=" + javaClass.genericSuperclass.toString())

        val vbClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
        Log.i("zx", "vbClass=" + vbClass.toString())
        val inflate = vbClass[position].getDeclaredMethod("inflate", LayoutInflater::class.java)
        return inflate.invoke(null, inflater) as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }
}