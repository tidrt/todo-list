package com.tidrt.todolist.model.db.dao

import android.content.ContentValues
import android.content.Context
import com.tidrt.todolist.model.db.DatabaseHelper
import com.tidrt.todolist.model.entities.Task

class TaskDAO(context : Context) : ITaskDAO {

    private val write = DatabaseHelper(context).writableDatabase;
    private val read = DatabaseHelper(context).readableDatabase;

    override fun save(task: Task): Boolean {
        val contentValue = ContentValues()
        contentValue.put(DatabaseHelper.TITLE, task.title)
        contentValue.put(DatabaseHelper.DATE, task.date)

        try {
            write.insert(
                DatabaseHelper.DB_TASK,
                null,
                contentValue
            )
        } catch (e : Exception){
            e.printStackTrace()
            return false
        }
        return true
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