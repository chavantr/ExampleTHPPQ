package com.passions.thehinduquestions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_start_practice.*

class StartPracticeActivity : AppCompatActivity() {

    private lateinit var practiceDatabaseHelper: PracticeDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_practice)
        practiceDatabaseHelper = PracticeDatabaseHelper(this, "upscprelimextra", null, 1, null)

        val myDatabase = MyDatabase(this, "upscprelim", null, 1)

        var practiceQuestionPaperMasterModel = PracticeQuestionPaperMasterModel()
        val masterId = practiceDatabaseHelper.createMasterQuestionPaper(practiceQuestionPaperMasterModel)

        var practiceDetails = myDatabase.getPracticeQuestion(masterId)

        practiceDatabaseHelper.createQuestionDetail(practiceDetails)

        lstPracticeQuestion.layoutManager = LinearLayoutManager(this)

        lstPracticeQuestion.adapter = StartPracticeAdapter(practiceDetails)
    }


}
