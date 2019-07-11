package com.example.yizazun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.yizazun.workmanager.UserWorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUserWorkManager()

    }

    fun setupUserWorkManager(){
        val work = PeriodicWorkRequestBuilder<UserWorkManager>(20, TimeUnit.SECONDS)
            .build()
        val workManager = WorkManager.getInstance()
        workManager.enqueue(work)
    }



}