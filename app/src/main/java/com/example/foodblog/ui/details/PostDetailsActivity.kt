package com.example.foodblog.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.foodblog.R
import com.example.foodblog.databinding.ActivityPostDetailsBinding
import com.example.foodblog.model.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class PostDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailsBinding
    private val postDetailsViewModel: PostDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val postId = intent.getStringExtra("postId")
        postDetailsViewModel.getPostById(postId!!)
        initView()
    }

    private fun initView() {
        lifecycleScope.launchWhenStarted {
            postDetailsViewModel.postDataState.collect {
                when(it){
                    is DataState.Loading-> binding.postDetails.bodyTextView.text = "I am Loading"
                    is DataState.Success-> {
                        binding.postDetails.apply {
                            bodyTextView.text = it.data.body
                        }
                        binding.postDetailImageView.load(it.data.imageUrl)
                    }
                    is DataState.Error-> binding.postDetails.bodyTextView.text = it.message
                }
            }
        }
    }
}