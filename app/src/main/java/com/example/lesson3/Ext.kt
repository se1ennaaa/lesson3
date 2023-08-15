package com.example.lesson3

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(text:String){
    Glide.with(this).load(text).into(this)
}