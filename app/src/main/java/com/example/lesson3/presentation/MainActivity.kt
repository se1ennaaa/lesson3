package com.example.lesson3.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.lesson3.R
import com.example.lesson3.core.BaseActivity
import com.example.lesson3.databinding.ActivityMainBinding
import com.example.lesson3.presentation.adapter.PlayListAdapter
import com.example.lesson3.presentation.viewModel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(){

    override fun inflateViewBinding () = ActivityMainBinding.inflate(layoutInflater)


    override val viewModel: MainViewModel by lazy{ ViewModelProvider(this)[MainViewModel::class.java]}
    private val adapter = PlayListAdapter()

    override fun setListener() {
        super.setListener()
        binding.recyclerView.adapter = adapter
        viewModel.getPlaylist().observe(this){
            adapter.addData(it.items)
        }
    }

}