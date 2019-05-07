package com.kos.exercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kos.exercise.adapters.QuestionsListAdapter
import com.kos.exercise.viewmodels.QuestionViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var questionsViewModel: QuestionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = QuestionsListAdapter(
            this, R.layout.item_question
        ) { data ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.INTENT_ID, data.question_id)
            startActivity(intent)
        }

        questionsViewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        questionsViewModel.questions.observe(this, Observer { questions ->
            questions?.let { adapter.updateList(it) }
        })

        questionsViewModel.reloadDataIfNeed(false)

        questionsViewModel.loadFailure.observe(this, Observer { e ->

            e?.let {
                if (questionsViewModel.questions.value.isNullOrEmpty()) {
                    val snackbar = Snackbar.make(list, R.string.error_load_data, Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction(R.string.error_load_data_retry_button) { v ->
                        snackbar.dismiss()
                        questionsViewModel.reloadDataIfNeed(true)
                    }
                    snackbar.show()
                }
            }
        })



        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


    }
}
