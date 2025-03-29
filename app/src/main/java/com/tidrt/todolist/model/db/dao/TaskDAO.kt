package com.tidrt.todolist.model.db.dao

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract.Data
import com.tidrt.todolist.model.db.DatabaseHelper
import com.tidrt.todolist.model.entities.Task
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy

class TaskDAO(context : Context) : ITaskDAO {

    private val write = DatabaseHelper(context).writableDatabase;
    private val read = DatabaseHelper(context).readableDatabase;

    override fun save(task: Task): Boolean {
        val contentValue = ContentValues()
        contentValue.put(DatabaseHelper.TITLE, task.title)

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
        val taskList = mutableListOf<Task>()
        val sql = "SELECT * FROM ${DatabaseHelper.DB_TASK};"
        val cursor = read.rawQuery(sql,null)

        val idIndex = cursor.getColumnIndex(DatabaseHelper.ID_TASK)
        val idTitle = cursor.getColumnIndex(DatabaseHelper.TITLE)
        val idDate = cursor.getColumnIndex(DatabaseHelper.DATE)

        while (cursor.moveToNext()){
            val idTask = cursor.getInt(idIndex)
            val title = cursor.getString(idTitle)
            val date = cursor.getString(idDate)

            taskList.add(
                Task(idTask, title, date)
            )
        }
        cursor.close()
        return taskList
    }

    override fun update(task: Task): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(idTask: Int): Boolean {
        TODO("Not yet implemented")
    }
}