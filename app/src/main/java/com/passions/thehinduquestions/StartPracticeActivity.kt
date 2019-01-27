package com.passions.thehinduquestions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdListener
import com.passions.thehinduquestions.binder.StartPracticeAdForAdapter
import kotlinx.android.synthetic.main.activity_start_practice.*

class StartPracticeActivity : AppCompatActivity() {

    private lateinit var practiceDatabaseHelper: PracticeDatabaseHelper

    private lateinit var practiceAdForAdapter: StartPracticeAdForAdapter

    private lateinit var practiceDetails: ArrayList<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_practice)

        practiceDatabaseHelper = PracticeDatabaseHelper(this, "upscprelimextra", null, 1)

        val myDatabase = MyDatabase(this, DatabaseConstants.databaseName, null, DatabaseConstants.version)

        var practiceQuestionPaperMasterModel = PracticeQuestionPaperMasterModel()

        val masterId = practiceDatabaseHelper.createMasterQuestionPaper(practiceQuestionPaperMasterModel)

        practiceDetails = myDatabase.getPracticeQuestion(masterId)

        practiceDatabaseHelper.createQuestionDetail(practiceDetails as List<PracticeQuestionDetailModel>)

        lstPracticeQuestion.layoutManager = LinearLayoutManager(this)

        practiceAdForAdapter = StartPracticeAdForAdapter(practiceDetails)

        lstPracticeQuestion.adapter = practiceAdForAdapter

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
                if (i % 4 == 0 && i != 0) {
                    practiceDetails.add(i, ad!!)
                }
            }

            practiceAdForAdapter.notifyDataSetChanged()
        }

        override fun onLoggingImpression(p0: Ad?) {

        }

    }


}
