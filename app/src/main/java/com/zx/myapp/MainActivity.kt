package com.zx.myapp

import android.os.Bundle
import com.zx.myapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.searchPhotosResult.observe(this, { result ->
            viewBinding.textView1.text = "描述为" + result.results[0].alt_description
        })
        viewModel.getImages()
    }
}