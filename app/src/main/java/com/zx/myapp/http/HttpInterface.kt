package com.zx.myapp.http


import com.zx.myapp.ImageBean
import com.zx.myapp.SearchPhotosResult
import retrofit2.http.GET
import retrofit2.http.Query


interface HttpInterface {
    @GET("/photos/random")
    suspend fun getImageRandom(@Query("count") count: Number): ArrayList<ImageBean>

    @GET("/search/photos")
    suspend fun searchPhotos(@Query("query") query: String): SearchPhotosResult
}