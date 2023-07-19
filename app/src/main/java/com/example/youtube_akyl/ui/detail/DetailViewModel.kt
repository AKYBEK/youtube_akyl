package com.example.youtube_akyl.ui.detail

import androidx.lifecycle.LiveData
import com.example.a5month_youtube.result.Resource
import com.example.youtube_akyl.App
import com.example.youtube_akyl.core.base.BaseViewModel
import com.example.youtube_akyl.data.model.PlaylistItem

class DetailViewModel : BaseViewModel() {

    fun getDetail(playListId: String?): LiveData<Resource<PlaylistItem>> {
        return App.repository.getDetail(playListId!!)
    }

}
