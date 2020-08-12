package com.example.pet.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pet.db.dao.PostDao
import com.example.pet.models.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}