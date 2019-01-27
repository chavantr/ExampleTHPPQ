package com.passions.thehinduquestions

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.practice_detail_row.view.*

class ResultDetailsAdapter(var practiceDetails: List<PracticeQuestionDetailModel>) : RecyclerView.Adapter<ResultDetailsAdapter.ResultDetailsViewHolder>() {

    var practiceDetailsMode: List<PracticeQuestionDetailModel> = practiceDetails


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ResultDetailsViewHolder {
        return ResultDetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.practice_detail_row, parent, false))
    }

    override fun getItemCount(): Int = practiceDetailsMode.size

    override fun onBindViewHolder(viewHolder: ResultDetailsViewHolder, position: Int) {

        viewHolder.lblQuestion.text = "${position.plus(1)}) " + practiceDetailsMode.get(position).question
        viewHolder.lblCorrectAnswer.text = "Correct Answer : " + practiceDetailsMode.get(position).correctAns.toUpperCase()
        viewHolder.lblYourAnswer.text = "Your Answer : " + practiceDetailsMode.get(position).yourAns.toUpperCase()

        if (!TextUtils.isEmpty(practiceDetailsMode.get(position).questionDetails)) {
            viewHolder.lblQuestionDescription.text = practiceDetailsMode.get(position).questionDetails.replace("\\n", "")
            viewHolder.lblQuestionDescription.visibility = View.VISIBLE
        } else {
            viewHolder.lblQuestionDescription.text = ""
            viewHolder.lblQuestionDescription.visibility = View.GONE
        }
        viewHolder.lblOptionA.text = "A) " + practiceDetailsMode.get(position).optionA
        viewHolder.lblOptionB.text = "B) " + practiceDetailsMode.get(position).optionB
        viewHolder.lblOptionC.text = "C) " + practiceDetailsMode.get(position).optionC
        viewHolder.lblOptionD.text = "D) " + practiceDetailsMode.get(position).optionD
    }


    inner class ResultDetailsViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val lblQuestion = view!!.lblQuestion
        val lblCorrectAnswer = view!!.lblCorrectAnswer
        val lblYourAnswer = view!!.lblYourAnswer
        val lblQuestionDescription = view!!.lblQuestionExtraDescription
        val lblOptionA = view!!.lblOptionA
        val lblOptionB = view!!.lblOptionB
        val lblOptionC = view!!.lblOptionC
        val lblOptionD = view!!.lblOptionD
    }

}