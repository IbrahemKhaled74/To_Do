package com.example.todo.myDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class todoItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id:Int?=null,
    @ColumnInfo
    var title:String?=null,
    @ColumnInfo
    var description:String?=null,
    @ColumnInfo
    var markAsDone:Boolean?=false,
    @ColumnInfo
    var date:Long?=null


):Serializable
