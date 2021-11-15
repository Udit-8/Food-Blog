package com.example.foodblog.ui.details

import androidx.lifecycle.*
import com.example.foodblog.data.repository.PostRepository
import com.example.foodblog.model.DataState
import com.example.foodblog.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class PostDetailsViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel(){
    private var _postDataState : MutableStateFlow<DataState<Post>> = MutableStateFlow(DataState.Loading())
    val postDataState : StateFlow<DataState<Post>> = _postDataState

    fun getPostById(postId : String) {
        viewModelScope.launch {
            postRepository.getPostById(postId).collect {
                _postDataState.value = it
            }
        }
    }
}