package com.kos.exercise.models.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kos.exercise.models.Question
import com.kos.exercise.models.dao.QuestionDao
import com.kos.exercise.net.Api

class QuestionsRepository(private val questionDao: QuestionDao) {
    val allQuestions: LiveData<List<Question>> = questionDao.getAll()
    val loadFailure: MutableLiveData<Exception> = MutableLiveData();

    private var lastLoadTime = 0L


    fun reloadDataIfNeed(forceReload: Boolean) {
        val currentTime = System.currentTimeMillis()
        loadFailure.postValue(null)
        if (forceReload || lastLoadTime + RELOAD_TIME < currentTime) {
            lastLoadTime = currentTime

            Api.load(Api.HOST + Api.QUESTIONS_API, questionDao,
                { count ->
                    //complete
                    loadFailure.postValue(null)
                },
                { e ->
                    //failure
                    loadFailure.postValue(e);

                    lastLoadTime = System.currentTimeMillis() - RELOAD_TIME
                })
        }
    }

    companion object {
        val RELOAD_TIME = 5 * 60 * 1000 //5 minutes
    }
}