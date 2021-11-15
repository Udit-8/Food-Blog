package com.example.foodblog.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodblog.data.local.PostCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM Posts")
    suspend fun getAllPosts() : List<PostCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post : PostCacheEntity)

    @Query("SELECT * FROM Posts WHERE id = :id")
    fun getPostById(id : String) : Flow<PostCacheEntity>
}