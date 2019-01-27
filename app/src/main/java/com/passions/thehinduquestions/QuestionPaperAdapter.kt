package com.passions.thehinduquestions

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_question_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class QuestionPaperAdapter(var solvedQuestionPapers: List<PracticeQuestionPaperMasterModel>) : RecyclerView.Adapter<QuestionPaperAdapter.QuestionPaperViewHolder>() {
    var solvedQuestionsMaster: List<PracticeQuestionPaperMasterModel> = solvedQuestionPapers
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): QuestionPaperViewHolder {
        return QuestionPaperViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_question_list, parent, false))
    }

    override fun getItemCount(): Int = solvedQuestionsMaster.size

    override fun onBindViewHolder(viewHolder: QuestionPaperViewHolder, position: Int) {

        viewHolder.lblName.text = solvedQuestionsMaster.get(position).name + " " + position.plus(1)
        viewHolder.lblDate.text = convertMilliSecondToDate(solvedQuestionsMaster.get(position).date.toLong())
        viewHolder.llContainer.setOnClickListener {
            val intent = Intent(it.context, ResultActivity::class.java)
            intent.putExtra("id", solvedQuestionsMaster.get(position).id)
            it.context.startActivity(intent)
        }
    }

    private fun convertMilliSecondToDate(date: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm a")
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(date)
        return "Date : " + formatter.format(calendar.time)
    }


    inner class QuestionPaperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var lblName = view.lblName
        var lblDate = view.lblDate
        var llContainer = view.llContainer
    }



}