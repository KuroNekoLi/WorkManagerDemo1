package com.example.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "FilteringWorker"
class FilteringWorker (context: Context, params: WorkerParameters) : Worker(context,params) {
    override fun doWork(): Result {
        try {
            for (i in 0 ..3000){
                Log.i(TAG, "Filtering $i ")
            }

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}