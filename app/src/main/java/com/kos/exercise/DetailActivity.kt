package com.kos.exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kos.exercise.adapters.holders.QuestionDetailHolder
import com.kos.exercise.viewmodels.QuestionDetailModelFactory
import com.kos.exercise.viewmodels.QuestionDetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var questionsViewModel: QuestionDetailViewModel
    private lateinit var viewHolder: QuestionDetailHolder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewHolder = QuestionDetailHolder(findViewById(R.id.layout))

        val questionId = intent?.getLongExtra(DetailActivity.INTENT_ID, 0) ?: 0

        questionsViewModel = ViewModelProviders.of(this, QuestionDetailModelFactory(this.application, questionId))
            .get(QuestionDetailViewModel::class.java)
        questionsViewModel.question.observe(this, Observer { question ->
            question?.let {
                viewHolder.bind(question)
            }
        })
    }

    companion object {
        const val INTENT_ID = "id"
    }
}

