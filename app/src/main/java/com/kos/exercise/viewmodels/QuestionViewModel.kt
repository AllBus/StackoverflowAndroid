package com.kos.exercise.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kos.exercise.db.QuestionsDB
import com.kos.exercise.models.Question
import com.kos.exercise.models.repositories.QuestionsRepository


class QuestionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuestionsRepository
    val questions: LiveData<List<Question>>
    val loadFailure: LiveData<Exception>

    init {
        val questionDao = QuestionsDB.getDatabase(application).qustionDao()
        repository = QuestionsRepository(questionDao)
        questions = repository.allQuestions
        loadFailure = repository.loadFailure
    }

    fun reloadDataIfNeed(forceReload: Boolean) {
        repository.reloadDataIfNeed(forceReload)
    }

}