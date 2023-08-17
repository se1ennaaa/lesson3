package com.example.lesson3.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lesson3.BuildConfig
import com.example.lesson3.core.RetrofitClient
import com.example.lesson3.data.ApiService
import com.example.lesson3.data.PlayList
import com.example.lesson3.data.PlaylistItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService by lazy { RetrofitClient.create() }

    fun getPlaylist(): LiveData<PlayList> {
        val data = MutableLiveData<PlayList>()
        apiService.getPlaylists(
            BuildConfig.API_KEY,
            "contentDetails,snippet",
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            5
        ).enqueue(object :Callback<PlayList>{
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful){
                data.value = response.body()
            }}

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                Log.e("OLOLO", "onFailure: ${t.message}", )
            }

        })

        return data
    }

    fun getPlaylistItem(id:String):LiveData<PlaylistItem>{
        val data = MutableLiveData<PlaylistItem>()
        apiService.getDetailPlaylist(BuildConfig.API_KEY,part="contentDetails,snippet",id,10).enqueue(
            object :Callback<PlaylistItem>{
                override fun onResponse(call: Call<PlaylistItem>, response: Response<PlaylistItem>) {
                    if(response.isSuccessful){
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlaylistItem>, t: Throwable) {
                    Log.e("OLOLO", "onFailure: ${t.message}", )
                }
            }

        )
        return data
    }

}