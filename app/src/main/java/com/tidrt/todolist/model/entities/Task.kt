package com.tidrt.todolist.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class Task(
    val idTask : Int, val title : String, val date : String
) : Serializable