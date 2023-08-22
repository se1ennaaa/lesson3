package com.example.lesson3.ui.video

import androidx.lifecycle.LiveData
import com.example.lesson3.App
import com.example.lesson3.core.BaseViewModel
import com.example.lesson3.data.PlayList
import com.example.youtube.core.network.result.Resource

class VideoViewModel:BaseViewModel() {

    fun getVideo(id:String): LiveData<Resource<PlayList>> {
        return App.repository.getVideo(id)
    }
}