package com.vasilyevskii.testtask.repository

import com.vasilyevskii.testtask.api.ApiService
import javax.inject.Inject

class PostsRepository
@Inject constructor(private val apiService: ApiService){

   suspend fun getPosts() = apiService.getPosts()

   suspend fun getCommentPost(postId: String) = apiService.getCommentPost(postId)
}