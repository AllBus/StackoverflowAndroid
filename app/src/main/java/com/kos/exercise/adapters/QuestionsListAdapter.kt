package com.kos.exercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.kos.exercise.adapters.holders.QuestionItemHolder
import com.kos.exercise.models.Question

class QuestionsListAdapter(
    private val context: Context, @LayoutRes private val layoutId: Int,
    clickBody: (Question) -> Unit
) :
    RecyclerView.Adapter<QuestionItemHolder>() {

    private val clickListener: View.OnClickListener = View.OnClickListener { v: View ->
        when (val data = v.tag) {
            is Question -> clickBody(data)
            else -> {
            }
        }
    }

    private var data: List<Question> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemHolder {
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        return QuestionItemHolder(view, clickListener)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: QuestionItemHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updateList(newList: List<Question>) {
        if (newList !== data) {
            data = newList
            notifyDataSetChanged()
        }
    }
}