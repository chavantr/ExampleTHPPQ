package com.passions.thehinduquestions

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.practice_question_row.view.*


class StartPracticeAdapter(var questions: Array<QuestionModel>) : RecyclerView.Adapter<StartPracticeAdapter.StartPracticeHolder>() {

    var question: Array<QuestionModel> = questions

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): StartPracticeHolder {
        return StartPracticeHolder(LayoutInflater.from(parent.context).inflate(R.layout.practice_question_row, parent, false))
    }

    override fun getItemCount(): Int = question.size

    override fun onBindViewHolder(viewHolder: StartPracticeHolder, position: Int) {
        viewHolder.lblQuestion.text = "${position.plus(1)}) " + questions.get(position).questionTitle
        if (!TextUtils.isEmpty(questions.get(position).questionDescription))
            viewHolder.lblQuestionDescription.text = questions.get(position).questionDescription
        else
            viewHolder.lblQuestionDescription.visibility = View.GONE
        viewHolder.lblOptionA.text = questions.get(position).optionA
        viewHolder.lblOptionB.text = questions.get(position).optionB
        viewHolder.lblOptionC.text = questions.get(position).optionC
        viewHolder.lblOptionD.text = questions.get(position).optionD

        viewHolder.btnSubmit.setOnClickListener {
            viewHolder.lblOptionA.visibility = View.GONE
            viewHolder.lblOptionB.visibility = View.GONE
            viewHolder.lblOptionC.visibility = View.GONE
            viewHolder.lblOptionD.visibility = View.GONE
        }
    }

    inner class StartPracticeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblQuestion = view.lblQuestionTitle
        val lblQuestionDescription = view.lblQuestionExtraDescription
        val lblOptionA = view.lblOptionA
        val lblOptionB = view.lblOptionB
        val lblOptionC = view.lblOptionC
        val lblOptionD = view.lblOptionD
        val btnSubmit = view.btnSubmit
    }

}