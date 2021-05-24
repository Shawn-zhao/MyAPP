package com.zx.myapp

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

class BaseActivity : AppCompatActivity() {
    public val viewModel by lazy(mode = LazyThreadSafetyMode.NONE) {
        getViewBinding(layoutInflater)
    }

    fun <VB: ViewBinding> getViewBinding(inflater: LayoutInflater, position:Int = 0):VB{
        val vbClass =  (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
        val inflate = vbClass[position].getDeclaredMethod("inflate", LayoutInflater::class.java)
        return  inflate.invoke(null, inflater) as VB
    }

}