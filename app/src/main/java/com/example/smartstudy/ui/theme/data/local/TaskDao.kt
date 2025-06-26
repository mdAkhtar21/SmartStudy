package com.example.smartstudy.ui.theme.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.smartstudy.ui.theme.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Upsert
    suspend fun upsert(task: Task)

    @Query("DELETE FROM TASK WHERE taskid=:taskId")
    suspend fun deleteTask(taskId:Int)

    @Query("DELETE FROM TASK WHERE taskSubjectId=:subjectId")
    suspend fun deleteTaskBySubjectId(subjectId:Int)

    @Query("SELECT * FROM TASK WHERE taskid=:taskId")
    suspend fun getTaskById(taskId:Int): Task?

    @Query("SELECT * FROM TASK WHERE taskSubjectId=:subjectId")
    fun getTaskForSubject(subjectId: Int): Flow<List<Task>>

    @Query("SELECT * FROM TASK")
    fun getAllTasks(): Flow<List<Task>>
}