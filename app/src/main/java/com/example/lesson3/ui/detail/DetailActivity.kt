package com.example.lesson3.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.lesson3.ConnectionLiveData
import com.example.lesson3.core.BaseActivity
import com.example.lesson3.data.PlayList
import com.example.lesson3.data.PlaylistItem
import com.example.lesson3.databinding.ActivityDetailBinding
import com.example.lesson3.ui.MainActivity
import com.example.lesson3.ui.MainActivity.Companion.KEY_API
import com.example.lesson3.ui.detail.adapter.DetailAdapter
import com.example.lesson3.ui.detail.viewModel.DetailViewModel
import com.example.lesson3.ui.video.VideoActivity
import com.example.youtube.core.network.result.Status

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private val modelPlaylist by lazy { intent.extras?.getSerializable(KEY_API) as PlayList.Item }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override val viewModel: DetailViewModel by lazy { ViewModelProvider(this)[DetailViewModel::class.java] }
    private val adapter = DetailAdapter(this::onClick)

    private fun onClick(item: PlaylistItem.Item) {
        val intent = Intent(this, VideoActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(PLAYLIST_ITEM_KEY, item)
        intent.putExtras(bundle)
        startActivity(intent)
        Toast.makeText(this, "OLOLO", Toast.LENGTH_SHORT).show()
    }

    override fun setListener() {
        super.setListener()
        binding.recyclerView.adapter = adapter
        viewModel.getPlaylistItem(modelPlaylist.id).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {

                    adapter.addList(it.data?.items as MutableList<PlaylistItem.Item>)
                    binding.tvDescription.text = modelPlaylist.snippet.description
                    binding.tvTitle.text = modelPlaylist.snippet.title
                    binding.progressBar.isVisible = false
                    binding.tvVideoCount.text = "${modelPlaylist.contentDetails.itemCount} video"
                }

                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                }

                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }

        }
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.tvBack.setOnClickListener {
            finish()
        }
    }

    override fun checkIntrnet() {
        super.checkIntrnet()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.btnTryAgain.setOnClickListener {
                    binding.noConnection.isVisible = false
                    binding.internetConnection.isVisible = true
                }
            } else {
                binding.noConnection.isVisible = true
                binding.internetConnection.isVisible = false
            }
        }

    }

    companion object{
        const val PLAYLIST_ITEM_KEY = "playlistItem"
    }
}