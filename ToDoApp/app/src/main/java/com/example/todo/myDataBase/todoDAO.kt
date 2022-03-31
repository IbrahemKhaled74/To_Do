package com.example.todo.myDataBase

import androidx.room.*
import java.util.*

@Dao
interface todoDAO {
  @Insert
  fun addTodo(todoItem: todoItem)
  @Update
  fun updateTodo(todoItem: todoItem)
  @Delete
  fun deleteTodo(todoItem: todoItem)
  @Query("select * from todoItem")
  fun getAllTodo():MutableList<todoItem>
  @Query("select * from todoItem where date =:date")
  fun getAllTodoByDate(date: Long):MutableList<todoItem>

}