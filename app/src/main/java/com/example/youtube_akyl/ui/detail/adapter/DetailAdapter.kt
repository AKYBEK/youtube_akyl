package com.example.youtube_akyl.ui.detail.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_akyl.core.ext.loadImage
import com.example.youtube_akyl.data.model.PlaylistItem
import com.example.youtube_akyl.databinding.ItemDetailBinding

class DetailAdapter(): RecyclerView.Adapter<DetailAdapter.PlaylistItemViewHolder>() {
    private var data = listOf<PlaylistItem.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<PlaylistItem.Item?>?) {
        this.data = list as List<PlaylistItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        return PlaylistItemViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PlaylistItemViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlaylistItem.Item) {
            binding.image.loadImage(item.snippet?.thumbnails?.high?.url!!)
            binding.tvTitle.text = item.snippet.title
        }
    }

}