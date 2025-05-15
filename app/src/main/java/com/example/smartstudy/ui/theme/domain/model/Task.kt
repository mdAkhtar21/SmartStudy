package com.example.smartstudy.ui.theme.domain.model

data class Task(
    val title: String,
    val Description: String,
    val dueDate: Long,
    val Priority: Int,
    val releatedToSubject: String,
    val isComplete: Boolean,
    val taskSubjectId: Int,
    val taskid: Int
)
