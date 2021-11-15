package com.example.foodblog.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.foodblog.databinding.ActivityMainBinding
import com.example.foodblog.model.DataState
import com.example.foodblog.model.Post
import com.example.foodblog.ui.details.PostDetailsActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var postAdapter : PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.getPosts()
        postAdapter = PostAdapter(this::onItemClicked)
        binding.postRecyclerView.adapter = postAdapter
        updateUi()
    }

    private fun updateUi() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.postState.collect {
                when(it){
                    is DataState.Loading->{

                    }
                    is DataState.Success->{
                        postAdapter.submitList(it.data)
                    }
                    is DataState.Error -> {

                    }
                }
            }
        }
    }

    private fun onItemClicked(post : Post){
        val intent = Intent(this,PostDetailsActivity::class.java)
        intent.putExtra("postId",post.id)
        startActivity(intent)

    }
}