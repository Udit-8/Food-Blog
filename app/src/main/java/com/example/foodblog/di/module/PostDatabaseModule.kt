package com.example.foodblog.di.module

import android.app.Application
import com.example.foodblog.data.local.PostDatabase
import com.example.foodblog.data.local.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PostDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) : PostDatabase = PostDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostDao(database: PostDatabase) : PostDao = database.getDao()
}