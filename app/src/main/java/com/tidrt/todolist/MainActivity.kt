package com.tidrt.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.tidrt.todolist.adapter.TaskAdapter
import com.tidrt.todolist.databinding.ActivityMainBinding
import com.tidrt.todolist.model.db.dao.TaskDAO
import com.tidrt.todolist.model.entities.Task
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater);
    }
    private var taskList = emptyList<Task>()
    private var taskAdapter : TaskAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }

        taskAdapter = TaskAdapter(
            {id -> acceptDelete(id)}
        )

        with(binding){
            rvTaskList.adapter = taskAdapter
            rvTaskList.layoutManager = LinearLayoutManager(binding.rvTaskList.context)
        }
    }

    private fun acceptDelete(id : Int) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmar Exclusão")
            .setMessage("Deseja confirmar a exclusão da tarefa?")
            .setPositiveButton("Sim"){_, _ ->
                val taskDAO = TaskDAO(this)
                taskDAO.delete(id)
                updateTaskList()
            }
            .setNegativeButton("Não"){_, _ ->

            }
            .create()
            .show()
    }

    private fun updateTaskList(){
        val taskDAO = TaskDAO(this)
        taskList = taskDAO.read()
        taskAdapter?.addTaskList(taskList)
    }

    override fun onStart() {
        super.onStart()
        updateTaskList()
    }
}