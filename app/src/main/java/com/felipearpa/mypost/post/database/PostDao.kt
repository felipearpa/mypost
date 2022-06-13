package com.felipearpa.mypost.post.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {

    @Insert
    suspend fun add(post: PostEntity)

    @Query("DELETE FROM post")
    suspend fun removeAll()

    @Query("SELECT * FROM post")
    suspend fun getAll(): List<PostEntity>

}