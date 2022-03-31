package com.example.todo.fragments

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.todo.ClearTime
import com.example.todo.R
import com.example.todo.myDataBase.myDB
import com.example.todo.myDataBase.todoItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class bottomSheet:BottomSheetDialogFragment() {
    lateinit var datePick: TextView
    lateinit var taskName:TextInputLayout
    lateinit var taskDes:TextInputLayout
    lateinit var addTodo: FloatingActionButton
    @RequiresApi(Build.VERSION_CODES.N)
    var calendar=Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate( R.layout.bottom_sheet,container,false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addTodo.setOnClickListener {
            if(isValid()) {
                addTodo(taskName.editText?.text.toString(),
                    taskDes.editText?.text.toString()
                    )

            }
        }

    }
    interface reloadTodoData{
        fun onDataChangedListener()
    }
    var reloadTodo:reloadTodoData?= null

    @RequiresApi(Build.VERSION_CODES.N)
    private fun addTodo(title:String, desc:String){
        val todo=todoItem(
            title = title,
            description = desc,
            date = calendar.ClearTime().time.time

        )
        Log.e("todo111111before", "getAllTodoByDate: "+calendar.time.time )

        myDB.getInstance(requireContext().applicationContext)
            .todoDao().addTodo(todo)
        reloadTodo?.onDataChangedListener()
        dismiss()


    }
    private fun isValid():Boolean{
        var isValid=true;
        if (taskName.editText?.text.toString().isBlank()){
            taskName.setError("Enter Your Task Name")
            isValid=false
        }else{
            taskName.error=null
        }
        if (taskDes.editText?.text.toString().isBlank()){
            taskDes.setError("Enter Your Task Description")
            isValid=false
        }else{
            taskDes.error=null
        }
        return isValid



    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun initView(){
        datePick=requireView().findViewById(R.id.select_date)
        taskName=requireView().findViewById(R.id.task_name)
        taskDes=requireView().findViewById(R.id.task_desc)
        addTodo=requireView().findViewById(R.id.add_todo)
        datePick.setText(
            ""+calendar.get(Calendar.DAY_OF_MONTH)+"/"
                    +(calendar.get(Calendar.MONTH)+1) +"/"
                    +calendar.get(Calendar.YEAR)
        )
        datePick.setOnClickListener{
            selectedDate()
        }


    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun selectedDate(){

      val date=DatePickerDialog(requireContext(),
          DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
              calendar.set(Calendar.DAY_OF_MONTH, day)
              calendar.set(Calendar.MONTH, month)
              calendar.set(Calendar.YEAR, year)
              datePick.setText(""+day+"/"+(month+1)+"/"+year)

          },calendar.get(Calendar.YEAR),
          calendar.get(Calendar.MONTH),
          calendar.get(Calendar.DAY_OF_MONTH)
      )
      date.show()

  }




}