package com.felipearpa.mypost.post.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity(
    @NonNull @ColumnInfo(name = "user_id") val userId: Int,
    @PrimaryKey @NonNull @ColumnInfo(name = "id") val id: Int,
    @NonNull @ColumnInfo(name = "title") val title: String,
    @NonNull @ColumnInfo(name = "body") val body: String,
    @NonNull @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
)