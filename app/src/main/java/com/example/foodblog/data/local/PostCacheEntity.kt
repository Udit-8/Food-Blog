package com.example.foodblog.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class PostCacheEntity(
    @PrimaryKey
    var id : String,
    @NonNull
    var title : String,
    @NonNull
    var body : String,
    @NonNull @ColumnInfo(name = "image_url")
    var imageUrl : String,
    @NonNull
    var author : String
)