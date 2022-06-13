package com.pipel.mypost.post.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PostRepository {

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("posts/{postId}")
    suspend fun getPost(@Path("postId") postId: Int): PostResponse

}