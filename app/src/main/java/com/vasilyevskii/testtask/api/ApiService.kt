package com.vasilyevskii.testtask.api

import com.vasilyevskii.testtask.models.CommentPostDTO
import com.vasilyevskii.testtask.models.PostDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): Response<List<PostDTO>>

    @GET("/comments")
    suspend fun getCommentPost(
        @Query("postId") postId: String
    ): Response<List<CommentPostDTO>>
}