package com.example.newsfeedr.data.remote

import com.example.newsfeedr.data.remote.dto.NewsResponse
import com.example.newsfeedr.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}