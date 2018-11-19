package com.passions.thehinduquestions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class QuestionPaperAdapter(var solvedQuestionPapers: Array<PracticeQuestionPaperMasterModel>) : RecyclerView.Adapter<QuestionPaperAdapter.QuestionPaperViewHolder>() {

    var solvedQuestionsMaster: Array<PracticeQuestionPaperMasterModel> = solvedQuestionPapers

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): QuestionPaperViewHolder {
        return QuestionPaperViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_question_list, parent, false))
    }

    override fun getItemCount(): Int = solvedQuestionsMaster.size

    override fun onBindViewHolder(viewHolder: QuestionPaperViewHolder, position: Int) {

    }


    inner class QuestionPaperViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

}