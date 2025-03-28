package com.tidrt.todolist.model.db.dao

import com.tidrt.todolist.model.entities.Task

interface ITaskDAO {
    fun save(task : Task) : Boolean;
    fun read() : List<Task>;
    fun update(task : Task) : Boolean;
    fun delete(idTask : Int) : Boolean;
}