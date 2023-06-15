package com.example.workmanagerdemo1

import android.annotation.SuppressLint
import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Date

private const val TAG = "UploadWorker"
class UploadWorker(context: Context,params:WorkerParameters) : Worker(context,params){
    companion object{
        const val KEY_WORKER = "key_worker"
    }
    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        try {
            val count = inputData.getInt(MainActivity.KEY_COUNT_VALUE,0)
            for (i in 0 until count){
                Log.i(TAG, "Uploading $i ")
//                SystemClock.sleep(180*1000) //增加此行可以看到Running狀態
            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            val outPutData = Data.Builder()
                .putString(KEY_WORKER,currentDate)
                .build()

            return Result.success(outPutData)
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}