package com.passions.thehinduquestions

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.daily_questions_list.view.*

class DailyQuestionPaperAdapter(questions: Array<QuestionModel>) : RecyclerView.Adapter<DailyQuestionPaperAdapter.QuestionPaperViewHolder>() {
    var questions: Array<QuestionModel> = questions
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): QuestionPaperViewHolder {
        return QuestionPaperViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.daily_questions_list, parent, false))
    }

    override fun getItemCount(): Int = questions.size
    override fun onBindViewHolder(viewHolder: QuestionPaperViewHolder, position: Int) {
        viewHolder.bntViewAnsswer.setOnClickListener {
            viewHolder.lblCorrectAnswer.visibility = View.VISIBLE
            viewHolder.btnShowDetails.visibility = View.VISIBLE
            questions.get(position).viewAnswer = true
        }

        viewHolder.btnShowDetails.setOnClickListener { view ->
            val intent = Intent(view.context, QuestionDetailsScrollActivity::class.java)
            intent.putExtra("extra", questions.get(position).fullDescription)
            intent.putExtra("type", questions.get(position).type)
            view.context.startActivity(intent)
        }

        if (questions.get(position).viewAnswer) {
            viewHolder.lblCorrectAnswer.visibility = View.VISIBLE
            viewHolder.btnShowDetails.visibility = View.VISIBLE
        } else {
            viewHolder.lblCorrectAnswer.visibility = View.GONE
            viewHolder.btnShowDetails.visibility = View.GONE
        }

        viewHolder.lblQuestion.text = "${position.plus(1)}) " + questions.get(position).questionTitle
        if (!TextUtils.isEmpty(questions.get(position).questionDescription))
            viewHolder.lblQuestionDescription.text = questions.get(position).questionDescription
        else
            viewHolder.lblQuestionDescription.visibility = View.GONE
        viewHolder.lblOptionA.text = "A) " + questions.get(position).optionA
        viewHolder.lblOptionB.text = "B) " + questions.get(position).optionB
        viewHolder.lblOptionC.text = "C) " + questions.get(position).optionC
        viewHolder.lblOptionD.text = "D) " + questions.get(position).optionD
        viewHolder.lblCorrectAnswer.text = questions.get(position).correctAnswer.toUpperCase()
    }

    inner class QuestionPaperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblQuestion = view.lblQuestionTitle
        val lblQuestionDescription = view.lblQuestionExtraDescription
        val lblOptionA = view.lblOptionA
        val lblOptionB = view.lblOptionB
        val lblOptionC = view.lblOptionC
        val lblOptionD = view.lblOptionD
        val bntViewAnsswer = view.btnShowAnswer
        val btnShowDetails = view.btnViewDescription
        val lblCorrectAnswer = view.lblCorrectAnswer
    }
}