package com.example.foodblog.data.remote

import com.example.foodblog.model.Post
import com.example.foodblog.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor():EntityMapper<PostNetworkEntity, Post> {
    override fun mapFromEntity(entity: PostNetworkEntity): Post {
        return Post(
            entity.id,
        entity.title,
        entity.body,
        entity.imgUrl,
        entity.author
        )
    }

    override fun mapToEntity(domainModel: Post): PostNetworkEntity {
        return PostNetworkEntity(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.imageUrl,
            domainModel.author
        )
    }

    fun mapFromEntityList(entityList: List<PostNetworkEntity>) : List<Post>{
        return entityList.map{
            mapFromEntity(it)
        }
    }
}