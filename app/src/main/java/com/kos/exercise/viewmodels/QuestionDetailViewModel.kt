package com.kos.exercise.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kos.exercise.db.QuestionsDB
import com.kos.exercise.models.Question

class QuestionDetailViewModel(application: Application, id: Long) : AndroidViewModel(application) {

    val question: LiveData<Question>

    init {
        val questionDao = QuestionsDB.getDatabase(application).qustionDao()
        question = questionDao.getById(id)
    }
}

