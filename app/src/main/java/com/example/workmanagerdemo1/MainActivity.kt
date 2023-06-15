package com.example.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart = findViewById<Button>(R.id.btn_start)
        textView = findViewById(R.id.textView)
        btnStart.setOnClickListener { setOneTimeWorkRequest() }
    }

    private fun setOneTimeWorkRequest(){
        val workManager = WorkManager.getInstance(applicationContext)
//        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
//            .build()
        val uploadRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()
        workManager.enqueue(uploadRequest)
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this, Observer {
                textView.text = it.state.name //有四種狀態SUCCEEDED RUNNING ENQUEUED BLOCKED
            })
    }


}