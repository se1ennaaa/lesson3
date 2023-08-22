package com.example.lesson3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.lesson3.data.PlayList
import com.example.lesson3.data.PlaylistItem
import com.example.lesson3.data.RemoteDataSource
import com.example.youtube.core.network.result.Resource
import kotlinx.coroutines.Dispatchers

class Repository {

    private val remoteDataSource = RemoteDataSource()

    fun getPlaylist(): LiveData<Resource<PlayList>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = remoteDataSource.getPlaylist()
            emit(response)
        }
    }

    fun getPlaylistItem(id: String): LiveData<Resource<PlaylistItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = remoteDataSource.getPlaylistItem(id)
            emit(response)
        }
    }

    fun getVideo(id: String): LiveData<Resource<PlayList>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = remoteDataSource.getVideo(id)
            emit(response)
        }
    }

}