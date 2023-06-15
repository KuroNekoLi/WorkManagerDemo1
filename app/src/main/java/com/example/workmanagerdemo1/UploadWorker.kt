package com.example.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "UploadWorker"
class UploadWorker(context: Context,params:WorkerParameters) : Worker(context,params){
    override fun doWork(): Result {
        try {
            for (i in 0..600){
                Log.i(TAG, "Uploading $i ")
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}