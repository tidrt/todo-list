package com.tidrt.todolist.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    DB_VERSION
) {
    companion object{
        const val DB_VERSION = 1
        const val DB_NAME = "task.db"
        const val DB_TASK = "tasks"

        const val ID_TASK = "id_task"
        const val TITLE = "title"
        const val DATE = "task_date"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val sql =   "CREATE TABLE IF NOT EXISTS $DB_TASK(" +
                    "$ID_TASK INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "$TITLE VARCHAR(100)," +
                    "$DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)"

        try {
            p0?.execSQL(sql)
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}