package com.example.whattocook.data.db.remote.api


import com.example.whattocook.Utill.Constants
import com.example.whattocook.Utill.Constants.Companion.API_KEY
import com.example.whattocook.domain.entity.MenuResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MenuAPI {
    @GET("v2/top-headlines")
    suspend fun getMenu(
            @Query("country")
            countryCode: String = "ru",
            @Query("page")
            pageNumber: Int = 1,
            @Query("apiKey")
            apiKey: String = API_KEY,
    ): Response<MenuResponse>

    @GET("v2/top-headlines")
    suspend fun getMenuByKeyWord(
        @Query("country")
            countryCode: String = "us",
        @Query("page")
            pageNumber: Int = 1,
        @Query("apiKey")
            apiKey: String = Constants.API_KEY,
        @Query("q")
            keyWord: String = ""
    ): Response<MenuResponse>
}