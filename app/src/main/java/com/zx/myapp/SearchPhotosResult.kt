package com.zx.myapp

import com.zx.myapp.ImageBean

data class SearchPhotosResult(
    val results: ArrayList<ImageBean>,
    val total: Int,
    val total_pages: Int
)