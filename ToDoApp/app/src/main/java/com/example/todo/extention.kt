package com.example.todo

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
fun Calendar.ClearTime():Calendar {
    this.clear(Calendar.MILLISECOND)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.HOUR)
    this.clear(Calendar.HOUR_OF_DAY)
    this.clear(Calendar.MILLISECONDS_IN_DAY)
    return this


}