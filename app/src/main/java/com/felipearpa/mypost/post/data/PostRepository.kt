package com.felipearpa.mypost.post.data

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface PostRepository {

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("posts/{postId}")
    suspend fun getPost(@Path("postId") postId: Int): PostResponse

    @DELETE("posts/{postId}")
    suspend fun removePost(@Path("postId") postId: Int)

}