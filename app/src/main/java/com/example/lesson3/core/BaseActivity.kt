package com.example.lesson3.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM: BaseViewModel>: AppCompatActivity() {
    protected lateinit var binding: VB
    protected abstract fun inflateViewBinding(): VB
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        checkIntrnet()
        setUI()
        setListener()
        setliveData()


    }

    open fun setliveData(){}

    open fun setListener(){}

    open fun setUI(){}

    open fun checkIntrnet(){}

}

