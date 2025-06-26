package com.example.smartstudy.ui.theme.domain.repository

import com.example.smartstudy.ui.theme.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun upsertTask(task: Task)

    suspend fun deleteTask(taskId:Int)

    suspend fun getTaskById(taskId:Int):Task?

    fun getUpComingTaskForSubject(subjectId:Int): Flow<List<Task>>

    fun getCompletedForTasksForSubject(subjectInt:Int):Flow<List<Task>>

    fun getAllUpcomingTasks():Flow<List<Task>>
}