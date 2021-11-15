package com.example.foodblog.di.module

import com.example.foodblog.data.local.CacheMapper
import com.example.foodblog.data.local.dao.PostDao
import com.example.foodblog.data.remote.NetworkMapper
import com.example.foodblog.data.remote.service.PostApiService
import com.example.foodblog.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(postApiService: PostApiService,
                          networkMapper: NetworkMapper,
                          cacheMapper: CacheMapper,
                          postDao: PostDao
    ) = PostRepository(postApiService,networkMapper,cacheMapper,postDao)
}