package com.example.todo

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.todo.myDataBase.myDB
import com.example.todo.myDataBase.todoItem
import com.google.android.material.textfield.TextInputLayout

class update : AppCompatActivity() {
    lateinit var datePicker: TextView
    lateinit var saveChange:TextView
    lateinit var taskName:TextInputLayout
    lateinit var taskDes:TextInputLayout
    @RequiresApi(Build.VERSION_CODES.N)
    var calendar=Calendar.getInstance()
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        initView()
        saveChanges()

    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun saveChanges(){
        saveChange.setOnClickListener {
            if (isValid()) {
                var todos = (intent.getSerializableExtra(Constant.todo)) as todoItem
                myDB.getInstance(this).todoDao()
                    .updateTodo(
                        todoItem(
                            id=todos.id,
                            title = taskName.editText?.text.toString(),
                            description = taskDes.editText?.text.toString(),
                            date =  calendar.ClearTime().time.time,

                        )
                    )
                //finish()
                onBackPressed()

            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun initView(){
        datePicker=findViewById(R.id.select_date)
        saveChange=findViewById(R.id.save)
        taskName=findViewById(R.id.title_update)
        taskDes=findViewById(R.id.desc_update)

        datePicker.setText(
            ""+calendar.get(Calendar.DAY_OF_MONTH)+"/"
                    +(calendar.get(Calendar.MONTH)+1) +"/"
                    +calendar.get(Calendar.YEAR)
        )
        datePicker.setOnClickListener {
            datePick()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun datePick(){
        val date=DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                date, year, month, day ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,day)

            datePicker.setText(""+day+"/"+(month+1)+"/"+year)
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
        )
        date.show()





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

}