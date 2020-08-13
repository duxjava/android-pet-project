package com.example.pet.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.pet.models.Post

class PostViewModel : BaseViewModel() {
    private val postTitle = MutableLiveData<String>("")
    private val postBody = MutableLiveData<String>("")

    fun bind(post: Post) {
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }

    val validTitle = MediatorLiveData<Boolean>().apply {
        addSource(postTitle) {
            value = it != ""
        }
    }

    val validBody = MediatorLiveData<Boolean>().apply {
        addSource(postBody) {
            value = it != ""
        }
    }
}