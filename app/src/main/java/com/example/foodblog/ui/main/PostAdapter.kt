package com.example.foodblog.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.foodblog.databinding.ItemPostBinding
import com.example.foodblog.model.Post
import com.example.foodblog.ui.main.viewHolder.PostViewHolder

class PostAdapter(private val onItemClicked : (Post) -> Unit) : ListAdapter<Post, PostViewHolder> (DiffCallBack){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context)));
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position),onItemClicked)
    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<Post>(){
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }

        }
    }
}