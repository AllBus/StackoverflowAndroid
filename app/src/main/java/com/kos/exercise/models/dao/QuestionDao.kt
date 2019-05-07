package com.kos.exercise.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kos.exercise.models.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM Question")
    fun getAll(): LiveData<List<Question>>

    @Query("SELECT * FROM Question WHERE question_id = :id")
    fun getById(id: Long): LiveData<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: List<Question>)

    @Delete
    fun delete(question: Question)

    @Query("DELETE FROM Question")
    fun clear()

    @Transaction
    fun update(questions: List<Question>) {
        clear()
        insert(questions)
    }

}