package com.example.foodblog.data.remote.service

import com.example.foodblog.data.remote.PostNetworkEntity
import retrofit2.http.GET

interface PostApiService {

    @GET("/DummyFoodiumApi/api/posts/")
    suspend fun getAllPosts() : List<PostNetworkEntity>

    companion  object {
        val BASE_URL = "https://patilshreyas.github.io/"
    }
}