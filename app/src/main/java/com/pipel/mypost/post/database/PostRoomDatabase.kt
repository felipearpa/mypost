package com.pipel.mypost.post.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(PostEntity::class)], version = 1)
abstract class PostRoomDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

}