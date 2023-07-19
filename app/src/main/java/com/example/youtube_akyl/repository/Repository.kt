package com.example.youtube_akyl.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.a5month_youtube.result.Resource
import com.example.youtube_akyl.data.RemoteDataSource
import com.example.youtube_akyl.data.model.PlaylistItem
import com.example.youtube_akyl.data.model.Playlists
import kotlinx.coroutines.Dispatchers

class Repository {

    private val dataSource: RemoteDataSource by lazy {
        RemoteDataSource()
    }

    fun getPlayLists(): LiveData<Resource<Playlists>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlayLists()
            emit(response)
        }
    }

    fun getDetail(playlistId: String): LiveData<Resource<PlaylistItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getDetail(playlistId)
            emit(response)
        }
    }
}