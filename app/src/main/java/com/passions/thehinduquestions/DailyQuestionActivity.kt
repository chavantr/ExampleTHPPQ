package com.passions.thehinduquestions

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdListener
import com.passions.thehinduquestions.binder.DailyQuestionAdAdapter
import kotlinx.android.synthetic.main.activity_daily_question.*
import java.util.*

class DailyQuestionActivity : AppCompatActivity() {


    private lateinit var questionList: ArrayList<Any>
    private lateinit var dailyQuestionAdapter: DailyQuestionAdAdapter
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_question)

        lstDailyQuestions.layoutManager = LinearLayoutManager(this)
        textToSpeech = TextToSpeech(this, textToSpeechListener)
        val myDatabase = MyDatabase(this, DatabaseConstants.databaseName, null, DatabaseConstants.version)

        val id = intent.getIntExtra("id", 0)
        if (null != id)
            questionList = myDatabase.getQuestionMonthWise(id)

        val nativeAd = NativeAd(this, "704548526584810_706693733036956")

        nativeAd.setAdListener(adListener)

        nativeAd.loadAd()

        dailyQuestionAdapter = DailyQuestionAdAdapter(questionList, textToSpeech)

        if (null != questionList)
        //lstDailyQuestions.adapter = DailyQuestionPaperAdapter(questionList)
            lstDailyQuestions.adapter = dailyQuestionAdapter
    }

    private val adListener = object : NativeAdListener {

        override fun onAdClicked(p0: Ad?) {

        }

        override fun onMediaDownloaded(p0: Ad?) {

        }

        override fun onError(p0: Ad?, p1: AdError?) {

        }

        override fun onAdLoaded(ad: Ad?) {
            for (i in questionList.indices) {
                if (i % 4 == 0 && i != 0) {
                    questionList.add(i, ad!!)
                }
            }

            dailyQuestionAdapter.notifyDataSetChanged()
        }

        override fun onLoggingImpression(p0: Ad?) {

        }

    }

    private var textToSpeechListener = TextToSpeech.OnInitListener { textToSpeech.language = Locale.US }


}
