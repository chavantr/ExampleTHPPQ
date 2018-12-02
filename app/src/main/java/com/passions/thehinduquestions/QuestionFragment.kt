package com.passions.thehinduquestions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_question.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class QuestionFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_question, container, false)

        view.lstQuestions.layoutManager = LinearLayoutManager(context)

        val myDatabase = MyDatabase(context, "upscprelim", null, 1)

        var lstMonthName = myDatabase.monthName

        if (null != lstMonthName)
            view.lstQuestions.adapter = QuestionListAdapter(lstMonthName)

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                QuestionFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
