package com.example.lesson3.presentation.viewModel

import androidx.lifecycle.LiveData
import com.example.lesson3.App
import com.example.lesson3.core.BaseViewModel
import com.example.lesson3.data.PlayList

class MainViewModel: BaseViewModel(){

    fun getPlaylist():LiveData<PlayList>{
        return App.repository.getPlaylist()
    }
}