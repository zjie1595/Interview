package com.zj.interview.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zj.interview.model.Interview
import com.zj.interview.myApplicationContext

@Database(entities = [Interview::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun interviewDao(): InterviewDao
}

val appDatabase: AppDatabase by lazy {
    Room.databaseBuilder(myApplicationContext, AppDatabase::class.java, "interview-db").build()
}

val interviewDao: InterviewDao by lazy { appDatabase.interviewDao() }