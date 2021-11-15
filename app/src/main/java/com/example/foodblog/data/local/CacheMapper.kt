package com.example.foodblog.data.local

import com.example.foodblog.model.Post
import com.example.foodblog.utils.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<PostCacheEntity, Post> {
    override fun mapFromEntity(entity: PostCacheEntity): Post {
        return Post(
            entity.id,
            entity.title,
            entity.body,
            entity.imageUrl,
            entity.author
        )
    }

    override fun mapToEntity(domainModel: Post): PostCacheEntity {
        return PostCacheEntity(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.imageUrl,
            domainModel.author
        )
    }

    fun mapFromEntityList(entityList: List<PostCacheEntity>) = entityList.map {
        mapFromEntity(it)
    }
}