package com.tidrt.todolist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tidrt.todolist.databinding.ActivityTaskBinding
import com.tidrt.todolist.model.db.dao.TaskDAO
import com.tidrt.todolist.model.entities.Task
import java.time.LocalDateTime
import java.util.Date

class TaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTaskBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnAdd.setOnClickListener {
                save()
            }
        }
    }

    private fun save() {
        if(binding.editTextTaskTitle.text.isNotEmpty()){
            val title = binding.editTextTaskTitle.text.toString();
            val task = Task(-1, title, "Default")
            val taskDAO = TaskDAO(this)

            if(taskDAO.save(task)){
                Log.i("info_db", "Tarefa criada com sucesso!")
                finish()
            } else {
                Toast.makeText(this, "Erro ao criar tarefa", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            Toast.makeText(this, "Preencha o campo com uma tarefa!", Toast.LENGTH_SHORT).show()
        }

        binding.editTextTaskTitle.setText("")
    }
}