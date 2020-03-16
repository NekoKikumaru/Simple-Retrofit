package com.example.simpleretrofit.api

import com.example.simpleretrofit.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostApiInterface {

    @GET("Posts")
    fun getPost(): Call<List<Post>>
}