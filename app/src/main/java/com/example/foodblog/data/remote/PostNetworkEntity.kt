package com.example.foodblog.data.remote

import com.squareup.moshi.Json

data class PostNetworkEntity(
    var id: String,
    var title: String,
    var body: String,
    @Json(name = "imageUrl")
    var imgUrl: String,
    var author: String,
)
