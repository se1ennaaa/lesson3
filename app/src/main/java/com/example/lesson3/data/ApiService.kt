package com.example.lesson3.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResult: Int,
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getDetailPlaylist(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResult: Int
    ):Response<PlaylistItem>

    @GET("videos")
    suspend fun getVideo(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("id") id:String,
    ):Response<PlayList>
}