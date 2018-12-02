package com.passions.thehinduquestions

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.practice_question_row.view.*


class StartPracticeAdapter(var questions: List<PracticeQuestionDetailModel>) : RecyclerView.Adapter<StartPracticeAdapter.StartPracticeHolder>() {

    var question: List<PracticeQuestionDetailModel> = questions


    private lateinit var practiceDatabaseHelper: PracticeDatabaseHelper

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): StartPracticeHolder {

        practiceDatabaseHelper = PracticeDatabaseHelper(parent.context, "upscprelimextra", null, 1, null)

        return StartPracticeHolder(LayoutInflater.from(parent.context).inflate(R.layout.practice_question_row, parent, false))
    }

    override fun getItemCount(): Int = question.size

    override fun onBindViewHolder(viewHolder: StartPracticeHolder, position: Int) {
        viewHolder.lblQuestion.text = "${position.plus(1)}) " + questions.get(position).question
        if (!TextUtils.isEmpty(questions.get(position).questionDetails)) {
            viewHolder.lblQuestionDescription.text = questions.get(position).questionDetails.replace("\\n", "")
            viewHolder.lblQuestionDescription.visibility = View.VISIBLE
        } else {
            viewHolder.lblQuestionDescription.visibility = View.GONE
            viewHolder.lblQuestionDescription.text = ""
        }

        viewHolder.lblOptionA.text = questions.get(position).optionA
        viewHolder.lblOptionB.text = questions.get(position).optionB
        viewHolder.lblOptionC.text = questions.get(position).optionC
        viewHolder.lblOptionD.text = questions.get(position).optionD


        if (question.get(position).submitted) {
            viewHolder.lblOptionA.visibility = View.GONE
            viewHolder.lblOptionB.visibility = View.GONE
            viewHolder.lblOptionC.visibility = View.GONE
            viewHolder.lblOptionD.visibility = View.GONE
            viewHolder.btnSubmit.visibility = View.GONE
        } else {
            viewHolder.lblOptionA.visibility = View.VISIBLE
            viewHolder.lblOptionB.visibility = View.VISIBLE
            viewHolder.lblOptionC.visibility = View.VISIBLE
            viewHolder.lblOptionD.visibility = View.VISIBLE
            viewHolder.btnSubmit.visibility = View.VISIBLE
        }


        viewHolder.btnSubmit.setOnClickListener {

            var yourAnswer: String

            if (viewHolder.lblOptionA.isChecked) {
                yourAnswer = "A"
            } else if (viewHolder.lblOptionB.isChecked) {
                yourAnswer = "B"
            } else if (viewHolder.lblOptionC.isChecked) {
                yourAnswer = "C"
            } else if (viewHolder.lblOptionD.isChecked) {
                yourAnswer = "D"
            } else {
                yourAnswer = ""
            }

            var practiceQuestionDetailModel = question.get(position)

            practiceQuestionDetailModel.yourAns = yourAnswer

            if (!question.get(position).submitted) {
                if (!TextUtils.isEmpty(yourAnswer)) {
                    val inserted = practiceDatabaseHelper.updateQuestionAnswer(practiceQuestionDetailModel)
                    print("Inserted $inserted")
                }
                question.get(position).submitted = true
            }

            viewHolder.lblOptionA.visibility = View.GONE
            viewHolder.lblOptionB.visibility = View.GONE
            viewHolder.lblOptionC.visibility = View.GONE
            viewHolder.lblOptionD.visibility = View.GONE
            it.visibility = View.GONE


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