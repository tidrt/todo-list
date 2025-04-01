package com.tidrt.todolist

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.tidrt.todolist.databinding.ActivityTaskBinding
import com.tidrt.todolist.model.db.dao.TaskDAO
import com.tidrt.todolist.model.entities.Task

class TaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTaskBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var task : Task? = null;
        val bundle = intent.extras
        if(bundle != null){
            task = bundle.getSerializable("task") as Task
            binding.editTextTaskTitle.setText(task.title)
        }

        binding.btnAdd.setOnClickListener {
            if (task != null) {
                updateTask(task)
            } else {
                if(binding.editTextTaskTitle.text.isNotEmpty()){
                    saveTask()
                }
            }
            binding.editTextTaskTitle.setText("")
        }
    }

    private fun updateTask(task : Task) {
        val title = binding.editTextTaskTitle.text.toString();
        val taskUpdated = Task(
            task.idTask, title, "Default"
        )

        val taskDAO = TaskDAO(this)
        if (taskDAO.update(taskUpdated)) {
            Log.i("info_db", "Tarefa atualizada com sucesso!")
            finish()
        } else {
            Toast.makeText(this, "Erro ao atualizar tarefa", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun saveTask() {
        val title = binding.editTextTaskTitle.text.toString();
        val task = Task(-1, title, "Default")
        val taskDAO = TaskDAO(this)

        if (taskDAO.save(task)) {
            Log.i("info_db", "Tarefa criada com sucesso!")
            finish()
        } else {
            Toast.makeText(this, "Erro ao criar tarefa", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}