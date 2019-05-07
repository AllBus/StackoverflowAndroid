package com.kos.exercise.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kos.exercise.models.Question
import com.kos.exercise.models.dao.QuestionDao

@Database(entities = [Question::class], version = 1)
public abstract class QuestionsDB : RoomDatabase() {
    abstract fun qustionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: QuestionsDB? = null

        fun getDatabase(context: Context): QuestionsDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionsDB::class.java,
                    "questions_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}