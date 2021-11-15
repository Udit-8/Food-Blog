package com.example.foodblog.data.repository

import android.util.Log
import com.example.foodblog.data.local.CacheMapper
import com.example.foodblog.data.local.dao.PostDao
import com.example.foodblog.data.remote.NetworkMapper
import com.example.foodblog.data.remote.service.PostApiService
import com.example.foodblog.model.DataState
import com.example.foodblog.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class PostRepository constructor(private val postApiService: PostApiService,
                                 private val networkMapper: NetworkMapper,
                                 private val cacheMapper: CacheMapper,
                                 private val postDao: PostDao
) {
    suspend fun getPosts() : Flow<DataState<List<Post>>> =
        flow{
            try{
                emit(DataState.Loading())
                val dataFromRemote = postApiService.getAllPosts()
                val postDataRemote = networkMapper.mapFromEntityList(dataFromRemote)
                for(post in postDataRemote)
                    postDao.insertPost(cacheMapper.mapToEntity(post))
                val dataFromLocal = postDao.getAllPosts()
                Log.i("dataFromLocal",dataFromLocal.toString())
                val postDataLocal = cacheMapper.mapFromEntityList(dataFromLocal)
                emit(DataState.Success(postDataLocal))
            }
            catch(e : Exception){
                emit(DataState.Error(e.toString()))
            }
        }
    fun getPostById(postId : String):Flow<DataState<Post>> = flow{
        try{
            postDao.getPostById(postId).collect {
                emit(DataState.Success(cacheMapper.mapFromEntity(it)))
            }
        }
        catch (e : Exception){
            emit(DataState.Error(e.toString()))
        }
    }
}