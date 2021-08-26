package com.example.whattocook.data.db.repository
import com.example.whattocook.data.db.remote.api.RetrofitInstance
import com.example.whattocook.domain.entity.MenuResponse
import retrofit2.Response

class MenuRepository : Repository {

    override suspend fun getMenu(countryCode: String): Response<MenuResponse> {
        return RetrofitInstance.api.getMenu(countryCode)
    }

    override suspend fun getMenuByKeyWord(keyWord: String): Response<MenuResponse> {
        return RetrofitInstance.api.getMenuByKeyWord(keyWord = keyWord)
    }
}