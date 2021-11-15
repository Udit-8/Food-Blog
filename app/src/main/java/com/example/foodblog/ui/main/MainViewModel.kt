package com.example.foodblog.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {
    private val _postState : MutableStateFlow<DataState<List<Post>>> = MutableStateFlow(DataState.Loading())
    val postState : StateFlow<DataState<List<Post>>> = _postState

    fun getPosts() = viewModelScope.launch{
        postRepository.getPosts().collect {
            _postState.value = it
        }
    }
}