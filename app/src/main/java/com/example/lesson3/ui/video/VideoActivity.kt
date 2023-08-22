package com.example.lesson3.ui.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.lesson3.R
import com.example.lesson3.core.BaseActivity
import com.example.lesson3.data.PlayList
import com.example.lesson3.data.PlaylistItem
import com.example.lesson3.databinding.ActivityVideoBinding
import com.example.lesson3.ui.detail.DetailActivity.Companion.PLAYLIST_ITEM_KEY
import com.example.youtube.core.network.result.Status

class VideoActivity : BaseActivity<ActivityVideoBinding, VideoViewModel>() {

    private val modelVideo by lazy {
        intent.extras?.getSerializable(PLAYLIST_ITEM_KEY) as PlaylistItem.Item
    }

    override fun inflateViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    val adapter by lazy { PlayVideoAdapter() }

    override val viewModel: VideoViewModel by lazy{
        ViewModelProvider(this)[VideoViewModel::class.java]
    }

    override fun setUI() {
        super.setUI()
        viewModel.getVideo(modelVideo.contentDetails.videoId).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    adapter.addList(it.data?.items as MutableList<PlayList.Item>)
                   // binding.progressBar.isVisible = false
                }

                Status.ERROR -> {
                   // binding.progressBar.isVisible = false
                }

                Status.LOADING -> {
                 //   binding.progressBar.isVisible = true
                }
            }
        }
    }


}