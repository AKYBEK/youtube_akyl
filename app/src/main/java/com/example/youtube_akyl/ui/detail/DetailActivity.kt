package com.example.youtube_akyl.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube_akyl.R
import com.example.youtube_akyl.core.base.BaseActivity
import com.example.youtube_akyl.databinding.ActivityDetailBinding
import com.example.youtube_akyl.ui.detail.adapter.DetailAdapter
import com.example.youtube_akyl.ui.playlist.PlaylistActivity

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: DetailAdapter

    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun initViewModel() {
        super.initViewModel()
        val getId = intent.getStringExtra("id")
        val getTitle = intent.getStringExtra("title")
        val getDesc = intent.getStringExtra("desc")
        val getCount = intent.getIntExtra("count" ,0)

        viewModel.getDetail(getId).observe(this) {
            it.data?.let { it1 -> adapter.addList(it1.items) }
            binding.tvTitle.text = getTitle
            binding.tvDesc.text = getDesc
            binding.tvCounterVideo.text = "$getCount video series"
        }
    }

    override fun initView() {
        super.initView()
        adapter = DetailAdapter()
        binding.rvDetail.layoutManager = LinearLayoutManager(this)
        binding.rvDetail.adapter = adapter
    }

    override fun initListener() {
        super.initListener()
        val result = intent.getStringExtra(PlaylistActivity.ID)
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}