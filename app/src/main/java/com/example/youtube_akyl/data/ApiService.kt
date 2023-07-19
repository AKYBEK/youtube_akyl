package com.example.youtube_akyl.data

import com.example.youtube_akyl.data.model.PlaylistItem
import com.example.youtube_akyl.data.model.Playlists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlayLists(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int = 20
    ): Response<Playlists>

    @GET("playlistItems")
    suspend fun playlistItems(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") channelId: String,
        @Query("maxResults") maxResults : Int = 30
    ): Response<PlaylistItem>
}