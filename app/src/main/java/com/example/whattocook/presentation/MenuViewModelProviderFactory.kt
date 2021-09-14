package com.example.whattocook.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whattocook.data.db.repository.MenuRepository
import com.example.whattocook.presentation.viewmodel.MenuViewModel

class MenuViewModelProviderFactory(val newsRepository: MenuRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MenuViewModel(newsRepository) as T
    }
}