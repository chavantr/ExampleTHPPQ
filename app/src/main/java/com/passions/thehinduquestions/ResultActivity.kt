package com.passions.thehinduquestions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdListener
import com.passions.thehinduquestions.binder.ResultDetailsAdForAdapter
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private lateinit var practiceDatabaseHelper: PracticeDatabaseHelper
    private lateinit var practiceDetails: ArrayList<Any>
    private lateinit var question: ResultDetailsAdForAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        lstPracticeDetails.layoutManager = LinearLayoutManager(this)

        practiceDatabaseHelper = PracticeDatabaseHelper(this, "upscprelimextra", null, 1)

        val myDatabase = MyDatabase(this, DatabaseConstants.databaseName, null, DatabaseConstants.version)

        practiceDetails = practiceDatabaseHelper.getQuestionDetails(intent.getIntExtra("id", 0))!!

        if (null != practiceDetails) {
            //lstPracticeDetails.adapter = ResultDetailsAdapter(practiceDetails!!)
            question = ResultDetailsAdForAdapter(practiceDetails!!)
            lstPracticeDetails.adapter = question
        }

        val nativeAd = NativeAd(this, "704548526584810_706693733036956")

        nativeAd.setAdListener(adListener)

        nativeAd.loadAd()
    }

    private val adListener = object : NativeAdListener {

        override fun onAdClicked(p0: Ad?) {

        }

        override fun onMediaDownloaded(p0: Ad?) {

        }

        override fun onError(p0: Ad?, p1: AdError?) {

        }

        override fun onAdLoaded(ad: Ad?) {

            for (i in practiceDetails.indices) {
                if (i % 3 == 0 && i != 0) {
                    practiceDetails.add(i, ad!!)
                }
            }

            question.notifyDataSetChanged()
        }

        override fun onLoggingImpression(p0: Ad?) {

        }

    }
}
