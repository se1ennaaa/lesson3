package com.example.lesson3.ui.playlist.viewModel

import androidx.lifecycle.LiveData
import com.example.lesson3.App
import com.example.lesson3.core.BaseViewModel
import com.example.lesson3.data.PlayList
import com.example.youtube.core.network.result.Resource

class MainViewModel: BaseViewModel(){

    fun getPlaylist():LiveData<Resource<PlayList>>{
        return App.repository.getPlaylist()
    }

}