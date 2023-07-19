package com.example.youtube_akyl.ui.playlist

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.a5month_youtube.result.Status
import com.example.youtube_akyl.core.base.BaseActivity
import com.example.youtube_akyl.core.ext.ConnectionLiveData
import com.example.youtube_akyl.data.model.Item
import com.example.youtube_akyl.databinding.ActivityPlaylistBinding
import com.example.youtube_akyl.ui.detail.DetailActivity
import com.example.youtube_akyl.ui.playlist.adapter.PlaylistAdapter

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {
    override fun inflateViewBinding(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    private var adapter = PlaylistAdapter(this::onClick)

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.playlists().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    it.data?.let { it1 -> adapter.setList(it1.items) }
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }

                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun checkConnection() {
        super.checkConnection()
        ConnectionLiveData(application).observe(this) { isConnection ->
            if (isConnection){
                binding.noInternetConnection.visibility = View.GONE
                binding.internetConnection.visibility = View.VISIBLE
            }else{
                binding.noInternetConnection.visibility = View.VISIBLE
                binding.internetConnection.visibility = View.GONE
            }
        }
    }

    private fun onClick(item: Item) {
        val intent = Intent(this@PlaylistActivity, DetailActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("title", item.snippet.title)
        intent.putExtra("desc", item.snippet.description)
        intent.putExtra("count", item.contentDetails.itemCount)
        startActivity(intent)
    }

    companion object {
        const val ID = "id"
    }
}