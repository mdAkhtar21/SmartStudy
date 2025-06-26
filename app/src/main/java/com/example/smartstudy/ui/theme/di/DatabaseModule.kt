package com.example.smartstudy.ui.theme.di

import android.app.Application
import androidx.room.Room
import com.example.smartstudy.ui.theme.data.local.AppDatabase
import com.example.smartstudy.ui.theme.data.local.SubjectDao
import com.example.smartstudy.ui.theme.data.local.TaskDao
import com.example.smartstudy.ui.theme.data.local.sessionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "studySmart"
        ).fallbackToDestructiveMigration() // Optional but helpful during dev
            .build()
    }


    @Provides
    @Singleton
    fun provideSubjectDao(database:AppDatabase):SubjectDao{
        return database.subjectDao()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database:AppDatabase): TaskDao {
        return database.taskDao()
    }

    @Provides
    @Singleton
    fun provideSessionDao(database:AppDatabase):sessionDao{
        return database.sessionDao()
    }

}