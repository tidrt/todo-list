package com.tidrt.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tidrt.todolist.databinding.TaskCardViewBinding
import com.tidrt.todolist.model.entities.Task

class TaskAdapter(private val listTask : List<Task>) : Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding : TaskCardViewBinding): ViewHolder(binding.root){
        val taskTitle : TextView = binding.txtTask
        val taskDate : TextView = binding.txtDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = TaskCardViewBinding.inflate(layoutInflater, parent, false)

        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val tasks = listTask[position]
        holder.taskTitle.text = tasks.title
        holder.taskDate.text = tasks.date
    }

    override fun getItemCount(): Int {
        return listTask.size
    }
}