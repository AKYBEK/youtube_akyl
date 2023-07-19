package com.example.youtube_akyl.data

import com.example.a5month_youtube.result.Resource
import com.example.youtube_akyl.BuildConfig
import com.example.youtube_akyl.core.network.BaseDataSource
import com.example.youtube_akyl.core.network.RetrofitClient
import com.example.youtube_akyl.data.model.PlaylistItem
import com.example.youtube_akyl.data.model.Playlists
import com.example.youtube_akyl.utils.Const

class RemoteDataSource : BaseDataSource() {
    private val apiService: ApiService = RetrofitClient.create()

    suspend fun getPlayLists(): Resource<Playlists> {
        return getResult {
            apiService.getPlayLists(
                BuildConfig.API_KEY,
                Const.part,
                Const.channelId
            )
        }
    }

    suspend fun getDetail(playlistId: String):Resource<PlaylistItem> {
        return getResult {
            apiService.playlistItems(
                BuildConfig.API_KEY,
                Const.part,
                playlistId
            )
        }
    }
}