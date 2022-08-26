package com.github.tweeny.api

import com.github.tweeny.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPost(): List<Post>
}
