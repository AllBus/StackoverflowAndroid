package com.kos.exercise.adapters.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kos.exercise.R
import com.kos.exercise.models.Question


class QuestionItemHolder(itemView: View, clickListener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {

    private val labelAnswerCount: TextView = itemView.findViewById(R.id.labelAnsweCount)
    private val labelTitle: TextView = itemView.findViewById(R.id.labelTitle)
    private val labelAuthor: TextView = itemView.findViewById(R.id.labelAuthor)
    private val card: View = itemView.findViewById(R.id.card)

    init {
        card.setOnClickListener(clickListener)
    }

    fun bind(questionListData: Question) {

        card.tag = questionListData

        labelAnswerCount.text = questionListData.answer_count.toString()
        labelTitle.text = questionListData.title
        labelAuthor.text = questionListData.owner.display_name
    }

}
