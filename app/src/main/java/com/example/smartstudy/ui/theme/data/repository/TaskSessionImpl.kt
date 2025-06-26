package com.example.smartstudy.ui.theme.data.repository

import com.example.smartstudy.ui.theme.data.local.TaskDao
import com.example.smartstudy.ui.theme.domain.model.Task
import com.example.smartstudy.ui.theme.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor (private val taskDao: TaskDao): TaskRepository {
    override suspend fun upsertTask(task: Task) {
        taskDao.upsert(task)
    }

    override suspend fun deleteTask(taskId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(taskId: Int): Task? {
        TODO("Not yet implemented")
    }

    override fun getUpComingTaskForSubject(subjectId: Int): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun getCompletedForTasksForSubject(subjectInt: Int): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun getAllUpcomingTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }
}