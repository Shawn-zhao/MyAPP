package com.zx.myapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

open class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    val viewBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        getViewBinding(layoutInflater)
    }

    val viewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        getViewModel()
    }

    private fun <VB : ViewBinding> getViewBinding(inflater: LayoutInflater, position: Int = 0): VB {
        val vbClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()[position]
        val inflate = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        return inflate.invoke(null, inflater) as VB
    }

    private fun getViewModel(position: Int = 1): VM {
        val vClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VM>>()[position]
        return ViewModelProvider(this).get(vClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }
}