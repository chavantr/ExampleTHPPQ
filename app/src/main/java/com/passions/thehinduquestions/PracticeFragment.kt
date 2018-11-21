package com.passions.thehinduquestions

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_practice.view.*

class PracticeFragment : Fragment() {

    private lateinit var myDatabaseHelper: PracticeDatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_practice, container, false)

        var practiceDatabaseHelper = PracticeDatabaseHelper(context, "upscprelim", null, 1, null)

        val solvedQuestions = practiceDatabaseHelper.getSolvedMasterPapers()

        view.lstPractice.layoutManager = LinearLayoutManager(context)

        if(null!=solvedQuestions)
        view.lstPractice.adapter = QuestionPaperAdapter(solvedQuestions!!)

        view.btnStartPractice.setOnClickListener { view ->
            val intent = Intent(view.context, StartPracticeActivity::class.java)
            view.context.startActivity(intent)
        }

        return view
    }

}
