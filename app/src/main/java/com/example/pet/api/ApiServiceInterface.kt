package com.example.pet.api

import com.example.pet.BuildConfig
import com.example.pet.models.Post
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServiceInterface {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>

    @POST("posts")
    fun createPost(@Body post: Post): Observable<Post>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }

}