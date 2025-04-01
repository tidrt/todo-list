package com.tidrt.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tidrt.todolist.databinding.TaskCardViewBinding
import com.tidrt.todolist.model.entities.Task

class TaskAdapter(val onClickDelete : (Int) -> Unit, val onClickUpdate: (Task) -> Unit) : Adapter<TaskAdapter.TaskViewHolder>() {

    private var listTask : List<Task> = emptyList()

    fun addTaskList(list : List<Task>){
        this.listTask = list
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(taskBinding : TaskCardViewBinding): ViewHolder(taskBinding.root){
        private val binding : TaskCardViewBinding = taskBinding
        fun bind(tasks: Task) {
            binding.txtTask.text = tasks.title
            binding.txtDate.text = tasks.date
            binding.btnDelete.setOnClickListener { onClickDelete(tasks.idTask) }
            binding.btnEdit.setOnClickListener {
                onClickUpdate(tasks)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = TaskCardViewBinding.inflate(layoutInflater, parent, false)

        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val tasks = listTask[position]
        holder.bind(tasks)
    }

    override fun getItemCount(): Int {
        return listTask.size
    }
}