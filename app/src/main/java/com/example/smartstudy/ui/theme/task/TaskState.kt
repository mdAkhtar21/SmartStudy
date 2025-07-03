package com.example.smartstudy.ui.theme.task

import com.example.smartstudy.ui.theme.Util.Priority
import com.example.smartstudy.ui.theme.domain.model.Subject

data class TaskState(
    val title: String = "",
    val description: String = "",
    val dueDate: Long? = null,
    val isTaskComplete: Boolean = false,
    val priority: Priority = Priority.LOW,
    val relatedToSubject: String? = null,
    val subjects: List<Subject> = emptyList(),
    val subjectId: Int? = null,
    val currentTaskId: Int? = null
)