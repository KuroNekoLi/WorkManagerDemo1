package com.example.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    companion object{
        const val KEY_COUNT_VALUE = "key_count"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart = findViewById<Button>(R.id.btn_start)
        textView = findViewById(R.id.textView)
        btnStart.setOnClickListener { setOneTimeWorkRequest() }
    }

    private fun setOneTimeWorkRequest(){
        val workManager = WorkManager.getInstance(applicationContext)

        val data : Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE,125)
            .build()

        val constraints = Constraints.Builder()
            .setRequiresCharging(true) //只有充電時會被執行
            .build()

        val uploadRequest = OneTimeWorkRequestBuilder<UploadWorker>()
//            .setConstraints(constraints)
            .setInputData(data)
            .build()

        workManager.enqueue(uploadRequest)
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this, Observer {
                textView.text = it.state.name //有四種狀態SUCCEEDED RUNNING ENQUEUED BLOCKED
                if (it.state.isFinished){
                    val data = it.outputData
                    val message = data.getString(UploadWorker.KEY_WORKER)
                    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
                }
            })
    }


}