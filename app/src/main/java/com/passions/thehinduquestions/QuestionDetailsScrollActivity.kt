package com.passions.thehinduquestions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_question_details_scroll.*
import kotlinx.android.synthetic.main.content_question_details_scroll.*
import com.facebook.ads.AdView
import com.facebook.ads.AdSize


class QuestionDetailsScrollActivity : AppCompatActivity() {

    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_details_scroll)
        setSupportActionBar(toolbar)
        title = "Type : " + intent.extras.getString("type")
        lblFullDescription.text = intent.extras.getString("extra")

        adView = AdView(this, "704548526584810_704549499918046", AdSize.BANNER_HEIGHT_50)

        banner_container.addView(adView)

        adView!!.loadAd()


        fab.setOnClickListener {

        }


    }
}
