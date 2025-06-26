package com.example.smartstudy.ui.theme.domain.repository

import com.example.smartstudy.ui.theme.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    suspend fun insertSession(session: Session)

    suspend fun deleteSession(session: Session)

    fun getAllSessions(): Flow<List<Session>>

    fun getRecentFiveSessions(): Flow<List<Session>>

    fun getRecentTenSessionForSubject(subjectId:Int):Flow<List<Session>>

    fun getTotalSessionDuration():Flow<Long>

    fun getTotalSessionDurationBySubjectId(subjectId: Int):Flow<Long>
}