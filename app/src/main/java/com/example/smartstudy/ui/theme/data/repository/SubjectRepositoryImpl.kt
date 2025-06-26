package com.example.smartstudy.ui.theme.data.repository

import com.example.smartstudy.ui.theme.data.local.SubjectDao
import com.example.smartstudy.ui.theme.domain.model.Subject
import com.example.smartstudy.ui.theme.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor (private val subjectDao:SubjectDao):SubjectRepository {

    override suspend fun upsertSubject(subject: Subject) {
       subjectDao.upsertSubject(subject)
    }

    override fun getTotalSubjectCount(): Flow<Int> {
        return subjectDao.getTotalSubjectCount()
    }

    override fun getTotalGoalHour(): Flow<Float> {
        return subjectDao.getTotalGoalHour()
    }

    override suspend fun deleteSubject(subjectInt: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getSubjectById(subjectInt: Int): Subject? {
        TODO("Not yet implemented")
    }

    override fun getAllSubjects(): Flow<List<Subject>> {
        return subjectDao.getAllSubjects()
    }
}