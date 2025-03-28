package com.tidrt.todolist.model.db.dao

import android.content.Context
import com.tidrt.todolist.model.db.DatabaseHelper
import com.tidrt.todolist.model.entities.Task

class TaskDAO(context : Context) : ITaskDAO {

    private val write = DatabaseHelper(context).writableDatabase;
    private val read = DatabaseHelper(context).readableDatabase;

    override fun save(task: Task): Boolean {
        TODO("Not yet implemented")
    }

    override fun read(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun update(task: Task): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(idTask: Int): Boolean {
        TODO("Not yet implemented")
    }
}