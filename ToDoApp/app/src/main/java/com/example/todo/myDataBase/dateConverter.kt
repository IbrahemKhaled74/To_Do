package com.example.todo.myDataBase

import androidx.room.TypeConverter
import java.util.*

class dateConverter {
    @TypeConverter
    fun fromDate(date: Date):Long{
        return date.time
    }
    @TypeConverter
    fun fromLong(time:Long):Date{
        return Date(time)
    }
}