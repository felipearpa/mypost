package com.pipel.mypost.user.data

import retrofit2.http.GET
import retrofit2.http.Path

interface UserRepository {

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): UserResponse

}