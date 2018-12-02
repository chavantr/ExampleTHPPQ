package com.passions.thehinduquestions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private lateinit var practiceDatabaseHelper: PracticeDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        lstPracticeDetails.layoutManager = LinearLayoutManager(this)
        practiceDatabaseHelper = PracticeDatabaseHelper(this, "upscprelimextra", null, 1, null)
        val practiceDetails = practiceDatabaseHelper.getQuestionDetails(intent.getIntExtra("id", 0))
        if (null != practiceDetails)
            lstPracticeDetails.adapter = ResultDetailsAdapter(practiceDetails!!)
    }
}
