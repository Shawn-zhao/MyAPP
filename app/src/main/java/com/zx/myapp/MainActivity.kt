package com.zx.myapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zx.myapp.databinding.ActivityMainBinding
import java.lang.reflect.ParameterizedType

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {
//    private val viewModel by viewModels<MainActivityViewModel>()

    //    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        viewBinding = ActivityMainBinding.inflate(layoutInflater)
//        val rootView: View = viewBinding.root
//        setContentView(rootView)

        viewModel.searchPhotosResult.observe(this, { result ->

            viewBinding.textView1.text = "描述为" + result.results[0].alt_description

        })
        viewModel.getImages()
    }
}