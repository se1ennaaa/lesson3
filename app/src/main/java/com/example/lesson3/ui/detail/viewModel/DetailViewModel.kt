package com.example.lesson3.ui.detail.viewModel

import androidx.lifecycle.LiveData
import com.example.lesson3.App
import com.example.lesson3.core.BaseViewModel
import com.example.lesson3.data.PlayList
import com.example.lesson3.data.PlaylistItem
import com.example.lesson3.repository.Repository

class DetailViewModel:BaseViewModel() {


    fun getPlaylistItem(id:String):LiveData<PlaylistItem>{
        return App.repository.getPlaylistItem(id)
    }
}