package com.passions.thehinduquestions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_question_details_scroll.*
import kotlinx.android.synthetic.main.content_question_details_scroll.*

class QuestionDetailsScrollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_details_scroll)
        setSupportActionBar(toolbar)
        title = "Type : " + intent.extras.getString("type")
        lblFullDescription.text = intent.extras.getString("extra")
        fab.setOnClickListener { view ->

        }
    }
}
