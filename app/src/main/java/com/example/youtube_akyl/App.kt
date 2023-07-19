package com.example.youtube_akyl

import android.app.Application
import com.example.youtube_akyl.repository.Repository

class App : Application() {

    companion object {
        val repository: Repository by lazy {
            Repository()
        }
    }
}