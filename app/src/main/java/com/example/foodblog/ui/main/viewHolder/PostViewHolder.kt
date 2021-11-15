package com.example.foodblog.ui.main.viewHolder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodblog.R
import com.example.foodblog.databinding.ItemPostBinding
import com.example.foodblog.model.Post

class PostViewHolder(private val binding : ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post : Post,onItemClicked : (Post) -> Unit){
        binding.apply {
            nameText.text = post.author
            titleText.text = post.title
            root.setOnClickListener{
                onItemClicked(post)
            }
            postImageView.load(post.imageUrl){
                placeholder(R.drawable.ic_photo)
                error(R.drawable.ic_broken_photo)
            }
        }
    }
}