package com.example.whattocook.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattocook.data.db.repository.MenuRepository
import com.example.whattocook.domain.entity.Article

import kotlinx.coroutines.launch

class MenuViewModel(private val newsRepository: MenuRepository) : ViewModel() {

    val breakingMenuListLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    val errorStateLiveData: MutableLiveData<String> = MutableLiveData()
    val loadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        loadingMutableLiveData.postValue(true)
        val response = newsRepository.getMenu(countryCode)

        loadingMutableLiveData.postValue(false)
        val body = response.body()
        if (!response.isSuccessful) {
            errorStateLiveData.postValue("Error")
            return@launch
        }
        breakingMenuListLiveData.postValue(body!!.articles)
    }

    fun getNewsByKeyWord(keyWord: String) = viewModelScope.launch {
        loadingMutableLiveData.postValue(true)
        val response = newsRepository.getMenuByKeyWord(keyWord)

        loadingMutableLiveData.postValue(false)
        val body = response.body()
        if (!response.isSuccessful) {
            errorStateLiveData.postValue("Error")
            return@launch
        }
        breakingMenuListLiveData.postValue(body!!.articles)
    }
}