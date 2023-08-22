package com.example.lesson3.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.lesson3.ConnectionLiveData
import com.example.lesson3.core.BaseActivity
import com.example.lesson3.data.PlayList
import com.example.lesson3.databinding.ActivityMainBinding
import com.example.lesson3.ui.adapter.PlayListAdapter
import com.example.lesson3.ui.detail.DetailActivity
import com.example.lesson3.ui.playlist.viewModel.MainViewModel
import com.example.youtube.core.network.result.Status

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val adapter = PlayListAdapter(this::onClick)

    private fun onClick(item: PlayList.Item) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(KEY_API, item)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun setListener() {
        super.setListener()
        binding.recyclerView.adapter = adapter
        viewModel.getPlaylist().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.addData(it.data?.items as MutableList<PlayList.Item>)
                    binding.progressBar.isVisible = false
                }

                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                      binding.progressBar.isVisible = false
                }

                Status.LOADING -> {
                     binding.progressBar.isVisible = true
                }
            }
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

    companion object {
        const val KEY_API = "OLOLOL"
    }
}


