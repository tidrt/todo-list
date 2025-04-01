package com.tidrt.todolist.model.db.dao

import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract.Data
import com.tidrt.todolist.model.db.DatabaseHelper
import com.tidrt.todolist.model.entities.Task

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
        val sql =   "SELECT ${DatabaseHelper.ID_TASK}, " +
                    "${DatabaseHelper.TITLE}, " +
                    "strftime('%d/%m/%Y %H:%M', ${DatabaseHelper.DATE}) ${DatabaseHelper.DATE} " +
                    "FROM ${DatabaseHelper.DB_TASK};"

        val cursor = read.rawQuery(sql,null)

        val idIndex = cursor.getColumnIndex(DatabaseHelper.ID_TASK)
        val titleIndex = cursor.getColumnIndex(DatabaseHelper.TITLE)
        val dateIndex = cursor.getColumnIndex(DatabaseHelper.DATE)

        while (cursor.moveToNext()){
            val idTask = cursor.getInt(idIndex)
            val title = cursor.getString(titleIndex)
            val date = cursor.getString(dateIndex)

            taskList.add(
                Task(idTask, title, date)
            )
        }
        cursor.close()
        return taskList
    }

    override fun update(task: Task): Boolean {
        val contentValue = ContentValues()
        contentValue.put(DatabaseHelper.TITLE, task.title)

        val args = arrayOf(
            task.idTask.toString()
        )
        try{
            write.update(
                DatabaseHelper.DB_TASK,
                contentValue,
                "${DatabaseHelper.ID_TASK} = ?",
                args
            )
        } catch (e: Exception){
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun delete(idTask: Int): Boolean {
        val args = arrayOf(
            idTask.toString()
        )
        try {
            write.delete(
                DatabaseHelper.DB_TASK,
                "${DatabaseHelper.ID_TASK} = ?",
                args
            )
        } catch (e: Exception){
            e.printStackTrace()
            return false
        }
        return true
    }
}