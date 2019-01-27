package com.passions.thehinduquestions

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdListener
import com.passions.thehinduquestions.binder.QuestionPaperAdForAdapter
import kotlinx.android.synthetic.main.fragment_practice.view.*


class PracticeFragment : Fragment() {

    private lateinit var myDatabaseHelper: PracticeDatabaseHelper

    private var solvedQuestions: ArrayList<Any>? = null

    private lateinit var questionAdapter: QuestionPaperAdForAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_practice, container, false)

        var practiceDatabaseHelper = PracticeDatabaseHelper(context, DatabaseConstants.databaseNameExtra, null, DatabaseConstants.versionExtra)

        val myDatabase = MyDatabase(context, DatabaseConstants.databaseName, null, DatabaseConstants.version)

        solvedQuestions = practiceDatabaseHelper.getSolvedMasterPapers()!!

        view.lstPractice.layoutManager = LinearLayoutManager(context)

        if (null != solvedQuestions && !solvedQuestions!!.isEmpty()) {
            //view.lstPractice.adapter = QuestionPaperAdapter(solvedQuestions!!)
            questionAdapter = QuestionPaperAdForAdapter(solvedQuestions!!)
            view.lstPractice.adapter = QuestionPaperAdForAdapter(solvedQuestions!!)
        }

        val nativeAd = NativeAd(context, "704548526584810_706693733036956")

        nativeAd.setAdListener(adListener)

        nativeAd.loadAd()

        view.cvContainer.setOnClickListener { view ->
            val intent = Intent(view.context, StartPracticeActivity::class.java)
            view.context.startActivity(intent)
        }

        return view



    }

    private val adListener = object : NativeAdListener {

        override fun onAdClicked(p0: Ad?) {

        }

        override fun onMediaDownloaded(p0: Ad?) {

        }

        override fun onError(p0: Ad?, p1: AdError?) {

        }

        override fun onAdLoaded(ad: Ad?) {

            if (null != solvedQuestions && !solvedQuestions!!.isEmpty()) {
                for (i in solvedQuestions!!.indices) {
                    if (i % 4 == 0 && i != 0) {
                        solvedQuestions!!.add(i, ad!!)
                    }
                }
                questionAdapter.notifyDataSetChanged()
            }
        }

        override fun onLoggingImpression(p0: Ad?) {

        }

    }

}
