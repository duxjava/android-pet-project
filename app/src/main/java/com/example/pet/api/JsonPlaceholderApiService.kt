package com.example.pet.api

import com.example.pet.models.Post
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonPlaceholderApiService {

    @GET("posts")
    fun getPostAll(): Observable<List<Post>>

    @POST("posts")
    fun addPost(@Body post: Post): Observable<Post>

    companion object Factory {
        fun create(): JsonPlaceholderApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

            return retrofit.create(JsonPlaceholderApiService::class.java)
        }
    }

}