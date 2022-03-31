package com.example.todo.myDataBase

import android.content.Context
import androidx.room.*

@Database(entities = [todoItem::class], version = 1)
@TypeConverters(dateConverter::class)
abstract class myDB:RoomDatabase() {
    abstract fun todoDao():todoDAO

    companion object{
        val dataBaseName="myDataBaseName"
        var myDatabase:myDB ?=null

        fun getInstance(context:Context):myDB{
            if (myDatabase == null) {
                myDatabase = Room.databaseBuilder(
                    context
                    ,myDB::class.java
                    ,dataBaseName
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return myDatabase!!
        }
    }

}