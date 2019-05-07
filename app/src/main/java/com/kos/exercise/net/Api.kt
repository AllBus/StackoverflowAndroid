package com.kos.exercise.net

import com.google.gson.GsonBuilder
import com.kos.exercise.models.QuestionList
import com.kos.exercise.models.dao.QuestionDao


object Api {

    const val HOST = "https://api.stackexchange.com"

    const val QUESTIONS_API = "/2.2/questions?order=desc&sort=activity&site=stackoverflow"


    private fun load(
        uri: String,
        constructor: (String) -> Int,
        onComplete: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val loader = DataAsyncLoader(
            uri,
            constructor,
            onComplete,
            onFailure
        )
        loader.execute()
    }

    fun load(
        uri: String,
        questionDao: QuestionDao,
        onComplete: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        load(
            uri,
            { json ->
                val gson = GsonBuilder().create()
                val items = gson.fromJson(json, QuestionList::class.java);
                questionDao.update(items.items)
                items.items.size
            },
            onComplete,
            onFailure
        )

    }


}