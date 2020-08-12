package com.example.pet.db

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.pet.viewmodel.CreatePostModel
import com.example.pet.viewmodel.MainActivityModel
import com.example.pet.viewmodel.PostViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts").build()
        if (modelClass.isAssignableFrom(MainActivityModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityModel(db.postDao()) as T
        }
        if (modelClass.isAssignableFrom(CreatePostModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreatePostModel(db.postDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}