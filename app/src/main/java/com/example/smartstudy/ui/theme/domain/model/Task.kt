package com.example.smartstudy.ui.theme.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val title: String,
    val Description: String,
    val dueDate: Long,
    val Priority: Int,
    val releatedToSubject: String,
    val isComplete: Boolean,
    val taskSubjectId: Int,
    @PrimaryKey(autoGenerate = true)
    val taskid: Int?=null
)
