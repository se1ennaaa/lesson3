package com.example.lesson3

import android.app.Application
import com.example.lesson3.repository.Repository

class App:Application() {

    companion object{
        val repository by lazy{ Repository()}
    }

}