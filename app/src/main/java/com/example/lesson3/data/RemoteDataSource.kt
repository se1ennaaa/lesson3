package com.example.lesson3.data

import com.example.lesson3.BuildConfig
import com.example.lesson3.core.RetrofitClient
import com.example.lesson3.core.base.BaseDataSource
import com.example.youtube.core.network.result.Resource


class RemoteDataSource() : BaseDataSource() {

    private val apiService: ApiService by lazy { RetrofitClient.create() }

    suspend fun getPlaylist(): Resource<PlayList> {
        return getResult {
            apiService.getPlaylists(
                BuildConfig.API_KEY, "snippet,contentDetails",
                "UCWOA1ZGywLbqmigxE4Qlvuw", 10
            )
        }
    }

    suspend fun getPlaylistItem(playlistItem: String): Resource<PlaylistItem> {
        return getResult {
            apiService.getDetailPlaylist(BuildConfig.API_KEY, part = "snippet,contentDetails",
                playlistId = playlistItem, maxResult = 10
            )
        }
    }


    suspend fun getVideo(id: String): Resource<PlayList> {
        return getResult {
            apiService.getVideo(apiKey = BuildConfig.API_KEY, part = "snippet,contentDetails", id)
        }
    }

}