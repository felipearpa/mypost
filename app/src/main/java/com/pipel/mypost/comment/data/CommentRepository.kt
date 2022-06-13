package com.pipel.mypost.comment.data

import retrofit2.http.GET
import retrofit2.http.Path

interface CommentRepository {

    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") postId: Int): List<CommentResponse>

}