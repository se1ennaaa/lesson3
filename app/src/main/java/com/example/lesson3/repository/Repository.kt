package com.example.lesson3.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lesson3.BuildConfig
import com.example.lesson3.core.RetrofitClient
import com.example.lesson3.data.ApiService
import com.example.lesson3.data.PlayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    val apiService: ApiService by lazy { RetrofitClient.create() }

    fun getPlaylist(): LiveData<PlayList> {
        val data = MutableLiveData<PlayList>()
        apiService.getPlaylists(
            BuildConfig.API_KEY,
            "contentDetails,snippet",
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            5
        ).enqueue(object :Callback<PlayList>{
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                Log.e("OLOLO", "onFailure: ${t.message}", )
            }

        })

        return data
    }

}