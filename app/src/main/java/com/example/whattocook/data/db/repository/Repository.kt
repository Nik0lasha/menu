package com.example.whattocook.data.db.repository

import com.example.whattocook.domain.entity.MenuResponse

import retrofit2.Response

interface Repository {
    suspend fun getMenu(countryCode : String) : Response<MenuResponse>
    suspend fun getMenuByKeyWord (keyWord : String) : Response<MenuResponse>

}

//TODO work with data room, retrofit, firebase
