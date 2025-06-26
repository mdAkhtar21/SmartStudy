package com.example.smartstudy.ui.theme.domain.repository

import com.example.smartstudy.ui.theme.domain.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {

    suspend fun upsertSubject(subject: Subject)

    fun getTotalSubjectCount(): Flow<Int>

    fun getTotalGoalHour(): Flow<Float>

    suspend fun deleteSubject(subjectInt:Int)

    suspend fun getSubjectById(subjectInt: Int):Subject?

    fun getAllSubjects():Flow<List<Subject>>
}