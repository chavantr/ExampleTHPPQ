package com.passions.thehinduquestions

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_question_list.view.*

class QuestionListAdapter : RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): QuestionViewHolder {
        return QuestionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_daily_question_list, parent, false))
    }

    override fun getItemCount(): Int = 50

    override fun onBindViewHolder(viewHolder: QuestionViewHolder, position: Int) {

        viewHolder.cvContainer.setOnClickListener {
            val intent = Intent(viewHolder.cvContainer.context, DailyQuestionActivity::class.java)
            viewHolder.cvContainer.context.startActivity(intent)
        }
    }


    inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var cvContainer = view.cvContainer


    }

}