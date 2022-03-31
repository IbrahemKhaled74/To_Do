package com.example.todo

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.myDataBase.todoItem


class todoAdaptor(var todoList:MutableList<todoItem>?=null):RecyclerView.Adapter<todoAdaptor.viewHolder>() {



    fun itemChanged(todoList:MutableList<todoItem>){
        this.todoList=todoList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item,parent,false)
        return viewHolder(view)
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item=todoList?.get(position)
        holder.todoName.setText(item?.title)
        holder.todoDesc.setText(item?.description)
            holder.todoName.setTextColor(Color.parseColor("#5D9CEC"))
            holder.todoDesc.setTextColor(Color.GRAY)
            holder.markAsDone.setBackgroundResource(R.drawable.button_radius)
            holder.line.setBackgroundResource(R.drawable.button_radius)

        if (item?.markAsDone==true){
            holder.todoName.setTextColor(Color.GREEN)
            holder.todoDesc.setTextColor(Color.GREEN)
            holder.markAsDone.setBackgroundResource(R.drawable.button_radius_green)
            holder.line.setBackgroundResource(R.drawable.button_radius_green)
        }

        if (onItemClicked!=null) {
            holder.leftView.setOnClickListener {
                onItemClicked?.onItemDelete(position, item!!)
            }
        }
        if (onItemClicked!=null) {
            holder.allView.setOnClickListener {
                onItemClicked?.onItemUpdate(item!!)
            }
        }
        holder.markAsDone.setOnClickListener {
            item?.markAsDone=true
            onItemClicked?.markAsDone(item!!)
                holder.todoName.setTextColor(Color.GREEN)
                holder.todoDesc.setTextColor(Color.GREEN)
                holder.markAsDone.setBackgroundResource(R.drawable.button_radius_green)
                holder.line.setBackgroundResource(R.drawable.button_radius_green)
        }


    }


    override fun getItemCount(): Int = todoList?.size?:0
    var onItemClicked:OnItemChangeListener?=null
interface OnItemChangeListener{
    fun onItemDelete(position: Int,item: todoItem)
    fun onItemUpdate(item: todoItem)
    fun markAsDone(item: todoItem)
}
    class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var todoName:TextView=itemView.findViewById(R.id.todo_title)
        var todoDesc:TextView=itemView.findViewById(R.id.todo_desc)
        var leftView:ImageView=itemView.findViewById(R.id.left_view)
        var allView:CardView=itemView.findViewById(R.id.card_view)
        var markAsDone:ImageView=itemView.findViewById(R.id.done)
        var line:View=itemView.findViewById(R.id.view)

    }


}
