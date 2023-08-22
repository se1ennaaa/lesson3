package com.example.lesson3.ui.detail

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

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private val modelPlaylist by lazy { intent.extras?.getSerializable(KEY_API) as PlayList.Item }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }
    override val viewModel: DetailViewModel by lazy { ViewModelProvider(this)[DetailViewModel::class.java] }
    private val adapter = DetailAdapter(this::onClick)

    private fun onClick(item: PlaylistItem.Item) {
        Toast.makeText(this, "OLOLO", Toast.LENGTH_SHORT).show()
    }
    override fun setListener() {
        super.setListener()
        binding.recyclerView.adapter = adapter
        viewModel.getPlaylistItem(modelPlaylist.id).observe(this) {
            adapter.addList(it.items as MutableList<PlaylistItem.Item>)
            binding.tvTitle.text = modelPlaylist.snippet.title
            binding.tvDescription.text = modelPlaylist.snippet.description
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

   }}