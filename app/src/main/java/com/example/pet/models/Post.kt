package com.example.pet.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @field:PrimaryKey
    var id: Int?,
    val userId: Int,
    val title: String,
    val body: String
)