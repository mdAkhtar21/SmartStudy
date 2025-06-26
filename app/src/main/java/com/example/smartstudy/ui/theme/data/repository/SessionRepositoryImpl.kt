package com.example.smartstudy.ui.theme.data.repository

import com.example.smartstudy.ui.theme.data.local.sessionDao
import com.example.smartstudy.ui.theme.domain.model.Session
import com.example.smartstudy.ui.theme.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor (private val sessionDao: sessionDao): SessionRepository {
    override suspend fun insertSession(session: Session) {
        sessionDao.insertSession(session)
    }

    override suspend fun deleteSession(session: Session) {
        TODO("Not yet implemented")
    }

    override fun getAllSessions(): Flow<List<Session>> {
        TODO("Not yet implemented")
    }

    override fun getRecentFiveSessions(): Flow<List<Session>> {
        TODO("Not yet implemented")
    }

    override fun getRecentTenSessionForSubject(subjectId: Int): Flow<List<Session>> {
        TODO("Not yet implemented")
    }

    override fun getTotalSessionDuration(): Flow<Long> {
        return sessionDao.getTotalSessionDuration()
    }

    override fun getTotalSessionDurationBySubjectId(subjectId: Int): Flow<Long> {
        TODO("Not yet implemented")
    }
}