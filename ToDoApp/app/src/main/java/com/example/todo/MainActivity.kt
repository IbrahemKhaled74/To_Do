package com.example.todo

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.todo.fragments.bottomSheet
import com.example.todo.fragments.list
import com.example.todo.fragments.setting
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var bottomSheet: bottomSheet
    lateinit var addButton: FloatingActionButton
    var fragmentListTodo=list()
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        bottomNavSelect()
        floatingActionClick()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)





    }
    private fun floatingActionClick(){
        addButton.setOnClickListener {
            bottomSheet.show(supportFragmentManager,"")
        }
        bottomSheet.reloadTodo=object :bottomSheet.reloadTodoData{
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onDataChangedListener() {
                fragmentListTodo.getAllTodoByDate()
            }
        }
    }
    private fun bottomNavSelect(){
        bottomNavigationView.setOnItemSelectedListener {
            if(it.itemId==R.id.todo){
                pushFragment(fragmentListTodo)
            }else if(it.itemId==R.id.setting){
                pushFragment(setting())
            }
            return@setOnItemSelectedListener true
        }
        bottomNavigationView.selectedItemId=R.id.todo
    }
    private fun initView(){
        bottomNavigationView=findViewById(R.id.bottom_navigation_view)
        bottomSheet= bottomSheet()
        addButton=findViewById(R.id.floating_Action_Button)


    }
    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}