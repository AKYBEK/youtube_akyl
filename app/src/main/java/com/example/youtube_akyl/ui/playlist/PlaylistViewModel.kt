package com.example.youtube_akyl.ui.playlist

import androidx.lifecycle.LiveData
import com.example.a5month_youtube.result.Resource
import com.example.youtube_akyl.App
import com.example.youtube_akyl.core.base.BaseViewModel
import com.example.youtube_akyl.data.model.Playlists

class PlaylistViewModel : BaseViewModel() {

    fun playlists(): LiveData<Resource<Playlists>> {
        return App.repository.getPlayLists()
    }
}
