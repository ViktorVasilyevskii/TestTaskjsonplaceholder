package com.vasilyevskii.testtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasilyevskii.testtask.models.CommentPostDTO
import com.vasilyevskii.testtask.models.PostDTO
import com.vasilyevskii.testtask.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel
@Inject constructor(
    private val postsRepository: PostsRepository
): ViewModel() {

    private val _responsePosts = MutableLiveData<List<PostDTO>>()
    val responsePosts: LiveData<List<PostDTO>> = _responsePosts

    private val _responseErrorPosts = MutableLiveData<String>()
    val responseErrorPosts: LiveData<String> = _responseErrorPosts

    fun getResponsePosts() = viewModelScope.launch {
        postsRepository.getPosts().let { responsePost ->
            if(responsePost.isSuccessful){
                _responsePosts.postValue(responsePost.body())
            }else {
                _responseErrorPosts.postValue(responsePost.message())
            }
        }
    }


    private val _responseCommentPost = MutableLiveData<List<CommentPostDTO>>()
    val responseCommentPost: LiveData<List<CommentPostDTO>> = _responseCommentPost

    private val _responseCommentPostError = MutableLiveData<String>()
    val responseCommentPostError: LiveData<String> = _responseCommentPostError

    fun getResponseCommentPost(postId: String) = viewModelScope.launch {
        postsRepository.getCommentPost(postId).let { responseComment ->
            if(responseComment.isSuccessful){
                _responseCommentPost.postValue(responseComment.body())
            }else{
                _responseCommentPostError.postValue(responseComment.message())
            }
        }
    }
}