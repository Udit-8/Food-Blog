package com.example.foodblog.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodblog.data.local.dao.PostDao

@Database(entities = [PostCacheEntity::class],version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun getDao() : PostDao

    companion object{
        const val DB_NAME = "post_db"

        @Volatile
        private var INSTANCE : PostDatabase? = null

        fun getInstance(context : Context) : PostDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostDatabase::class.java,
                    DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}