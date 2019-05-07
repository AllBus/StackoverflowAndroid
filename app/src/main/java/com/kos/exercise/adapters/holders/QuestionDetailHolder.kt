package com.kos.exercise.adapters.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kos.exercise.R
import com.kos.exercise.models.Question
import com.kos.exercise.utils.Converter

class QuestionDetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val labelTitle: TextView = itemView.findViewById(R.id.labelTitle)
    private val labelOwner: TextView = itemView.findViewById(R.id.labelOwner)
    private val labelDate: TextView = itemView.findViewById(R.id.labelDate)
    private val labelAnswerCount: TextView = itemView.findViewById(R.id.labelAnswerCount)
    private val labelLink: TextView = itemView.findViewById(R.id.labelLink)

    init {

    }

    fun bind(questionListData: Question) {

        labelTitle.text = questionListData.title
        labelOwner.text = questionListData.owner.display_name
        labelDate.text = Converter.toDate(questionListData.creation_date)
        labelAnswerCount.text = questionListData.answer_count.toString()
        labelLink.text = questionListData.link

    }

}