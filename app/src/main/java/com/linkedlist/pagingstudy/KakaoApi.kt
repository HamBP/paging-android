package com.linkedlist.pagingstudy

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApi {
    @GET("v2/search/web")
    suspend fun request(
        @Header("Authorization")key: String = BuildConfig.API_KEY,
        @Query("query")query: String = "카카오",
        @Query("sort")sort: String = "accuracy",
        @Query("page")page: Int = 1,
        @Query("size")size: Int = 20,
    ): SearchResponse<Document>
}