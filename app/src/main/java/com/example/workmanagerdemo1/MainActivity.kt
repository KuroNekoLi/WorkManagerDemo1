package com.example.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart = findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener { setOneTimeWorkRequest() }
    }

    private fun setOneTimeWorkRequest(){
//        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
//            .build()
        val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()
        WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
    }


}