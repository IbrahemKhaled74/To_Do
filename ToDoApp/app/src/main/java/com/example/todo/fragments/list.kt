package com.example.todo.fragments

import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragAndDropPermissions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.example.todo.*
import com.example.todo.myDataBase.myDB
import com.example.todo.myDataBase.todoItem
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.zerobranch.layout.SwipeLayout

class list : Fragment() {
    lateinit var calanderView:MaterialCalendarView
    lateinit var todoRecyclerView: RecyclerView
    lateinit var todoAdaptor: todoAdaptor
    lateinit var todo:MutableList<todoItem>

    @RequiresApi(Build.VERSION_CODES.N)
    var calendar=Calendar.getInstance()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initView(){
        calanderView=requireView().findViewById(R.id.calendarView)
        todoRecyclerView=requireView().findViewById(R.id.RV)
        todoAdaptor= todoAdaptor()
        todoRecyclerView.adapter=todoAdaptor
        calanderView.selectedDate=CalendarDay.today()
        calanderView.setOnDateChangedListener { widget, date, selected ->
            calendar.set(Calendar.DAY_OF_MONTH,date.day)
            calendar.set(Calendar.MONTH,date.month-1)
            calendar.set(Calendar.YEAR,date.year)
            getAllTodoByDate()

        }


        todoAdaptor.onItemClicked=object :todoAdaptor.OnItemChangeListener{
            override fun onItemDelete(position: Int,todoItem: todoItem) {
                todo.removeAt(position)
                todoAdaptor.notifyItemRemoved(position)
                myDB.getInstance(requireContext()).todoDao()
                    .deleteTodo(todoItem)
                todoAdaptor.itemChanged(todo)
            }
            override fun onItemUpdate( item: todoItem) {
                val intent=Intent(requireActivity(),update()::class.java)
                    .putExtra(Constant.todo,item)
                startActivity(intent)
            }

            override fun markAsDone(item: todoItem) {
                val items=item
                items.markAsDone=true
                myDB.getInstance(requireContext()).todoDao()
                    .updateTodo(items)
                getAllTodoByDate()
            }


        }

    }





    @RequiresApi(Build.VERSION_CODES.N)
     fun getAllTodoByDate(){
        if (context==null)return

        todo= myDB.getInstance(requireContext())
            .todoDao().getAllTodoByDate( calendar.ClearTime().time.time)
        Log.e("todo111111", "getAllTodoByDate: "+calendar.time.time )
        todoAdaptor.itemChanged(todo)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()
        getAllTodoByDate()
    }



}
