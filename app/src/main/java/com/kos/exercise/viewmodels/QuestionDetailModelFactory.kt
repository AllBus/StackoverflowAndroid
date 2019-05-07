package com.kos.exercise.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuestionDetailModelFactory(private val application: Application, private val id: Long) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == QuestionDetailViewModel::class.java) {
            QuestionDetailViewModel(application, id) as T
        } else super.create(modelClass)
    }
}