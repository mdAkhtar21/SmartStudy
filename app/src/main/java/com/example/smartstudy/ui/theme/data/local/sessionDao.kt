package com.example.smartstudy.ui.theme.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.smartstudy.ui.theme.domain.model.Session
import kotlinx.coroutines.flow.Flow

@Dao
interface sessionDao {
    @Upsert
    suspend fun insertSession(session: Session)

    @Delete
    suspend fun deleteSession(session: Session)

    @Query("SELECT * FROM SESSION")
    fun getAllSessions(): Flow<List<Session>>

    @Query("SELECT * FROM Session WHERE sessionSubjectId = :subjectId")
    fun getRecentSessionsForSubject(subjectId: Int): Flow<List<Session>>

    @Query("SELECT SUM(Duration) FROM SESSION")
    fun getTotalSessionDuration():Flow<Long>

    @Query("SELECT SUM(Duration) FROM SESSION WHERE sessionSubjectId=:subjectId")
    fun getTotalSessionDurationForSubjectId(subjectId: Int): Flow<Long>

    @Query("DELETE FROM SESSION WHERE sessionSubjectId=:subjectId")
    fun deleteSessionBySubjectId(subjectId: Int)
}