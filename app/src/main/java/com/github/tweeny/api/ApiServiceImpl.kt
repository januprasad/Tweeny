package com.github.tweeny.api

import com.github.tweeny.model.Post
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost(): List<Post> = apiService.getPost()
}
