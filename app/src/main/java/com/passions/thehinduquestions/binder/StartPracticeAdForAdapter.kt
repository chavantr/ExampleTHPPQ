package com.passions.thehinduquestions.binder

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.ads.Ad
import com.facebook.ads.NativeAd
import com.passions.thehinduquestions.*
import kotlinx.android.synthetic.main.native_new_item_row.view.*
import kotlinx.android.synthetic.main.practice_question_row.view.*

class StartPracticeAdForAdapter(questions: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var lstQuestions: List<Any> = questions

    private lateinit var myDatabase: MyDatabase
    private lateinit var practiceDatabaseHelper: PracticeDatabaseHelper

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): RecyclerView.ViewHolder {
        practiceDatabaseHelper = PracticeDatabaseHelper(parent.context, "upscprelimextra", null, 1)
        val myDatabase = MyDatabase(parent.context, DatabaseConstants.databaseName, null, DatabaseConstants.version)
        return when (itemType) {
            QUESTION -> StartPracticeHolder(LayoutInflater.from(parent.context).inflate(R.layout.practice_question_row, parent, false))
            NATIVE -> NativeAdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.native_new_item_row, parent, false))
            else -> StartPracticeHolder(LayoutInflater.from(parent.context).inflate(R.layout.practice_question_row, parent, false))
        }
    }

    override fun getItemCount(): Int = lstQuestions.size


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        val itemType = getItemViewType(position)

        if (itemType == QUESTION) {

            var questions = lstQuestions.get(position) as PracticeQuestionDetailModel

            val recipeViewHolder = viewHolder as StartPracticeHolder

            recipeViewHolder.lblQuestion.text = questions.question
            if (!TextUtils.isEmpty(questions.questionDetails)) {
                recipeViewHolder.lblQuestionDescription.text = questions.questionDetails.replace("\\n", "")
                recipeViewHolder.lblQuestionDescription.visibility = View.VISIBLE
            } else {
                recipeViewHolder.lblQuestionDescription.visibility = View.GONE
                recipeViewHolder.lblQuestionDescription.text = ""
            }

            recipeViewHolder.lblOptionA.text = questions.optionA
            recipeViewHolder.lblOptionB.text = questions.optionB
            recipeViewHolder.lblOptionC.text = questions.optionC
            recipeViewHolder.lblOptionD.text = questions.optionD


            if (questions.submitted) {
                recipeViewHolder.lblOptionA.visibility = View.GONE
                recipeViewHolder.lblOptionB.visibility = View.GONE
                recipeViewHolder.lblOptionC.visibility = View.GONE
                recipeViewHolder.lblOptionD.visibility = View.GONE
                recipeViewHolder.btnSubmit.visibility = View.GONE
            } else {
                recipeViewHolder.lblOptionA.visibility = View.VISIBLE
                recipeViewHolder.lblOptionB.visibility = View.VISIBLE
                recipeViewHolder.lblOptionC.visibility = View.VISIBLE
                recipeViewHolder.lblOptionD.visibility = View.VISIBLE
                recipeViewHolder.btnSubmit.visibility = View.VISIBLE
            }


            recipeViewHolder.btnSubmit.setOnClickListener {

                var yourAnswer: String

                if (recipeViewHolder.lblOptionA.isChecked) {
                    yourAnswer = "A"
                } else if (recipeViewHolder.lblOptionB.isChecked) {
                    yourAnswer = "B"
                } else if (recipeViewHolder.lblOptionC.isChecked) {
                    yourAnswer = "C"
                } else if (recipeViewHolder.lblOptionD.isChecked) {
                    yourAnswer = "D"
                } else {
                    yourAnswer = ""
                }

                var practiceQuestionDetailModel = questions

                practiceQuestionDetailModel.yourAns = yourAnswer

                if (!questions.submitted) {
                    if (!TextUtils.isEmpty(yourAnswer)) {
                        val inserted = practiceDatabaseHelper.updateQuestionAnswer(practiceQuestionDetailModel)
                    }
                    questions.submitted = true
                    recipeViewHolder.lblOptionA.visibility = View.GONE
                    recipeViewHolder.lblOptionB.visibility = View.GONE
                    recipeViewHolder.lblOptionC.visibility = View.GONE
                    recipeViewHolder.lblOptionD.visibility = View.GONE
                    it.visibility = View.GONE
                }

            }


        } else if (itemType == NATIVE) {
            val nativeAdViewHolder = viewHolder as NativeAdViewHolder
            var nativeAd = lstQuestions.get(position) as NativeAd
            nativeAdViewHolder.nativeAdTitle.text = nativeAd.advertiserName
            nativeAdViewHolder.nativeAdBody.text = nativeAd.adBodyText
            nativeAdViewHolder.nativeAdSocialContext.text = nativeAd.adSocialContext
            if (nativeAd.hasCallToAction()) {
                nativeAdViewHolder.nativeAdCallToAction.visibility = View.VISIBLE
            } else {
                nativeAdViewHolder.nativeAdCallToAction.visibility = View.GONE
            }
            nativeAdViewHolder.nativeAdCallToAction.text = nativeAd.adCallToAction
            nativeAdViewHolder.sponsoredLabel.text = nativeAd.sponsoredTranslation
            val clickableViews = ArrayList<View>()
            clickableViews.add(nativeAdViewHolder.nativeAdTitle)
            clickableViews.add(nativeAdViewHolder.nativeAdCallToAction)
            nativeAd.registerViewForInteraction(
                    nativeAdViewHolder.nativeAdMedia,
                    nativeAdViewHolder.nativeAdIcon,
                    clickableViews)
        }


    }


    override fun getItemViewType(position: Int): Int {
        val item = lstQuestions[position]
        return when (item) {
            is PracticeQuestionDetailModel -> QUESTION
            is Ad -> NATIVE
            else -> -1
        }
    }

    inner class NativeAdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nativeAdIcon = view.native_ad_icon
        var nativeAdTitle = view.native_ad_title
        var nativeAdMedia = view.native_ad_media
        var nativeAdSocialContext = view.native_ad_social_context
        var nativeAdBody = view.native_ad_body
        var sponsoredLabel = view.native_ad_sponsored_label
        var nativeAdCallToAction = view.native_ad_call_to_action
    }

    inner class StartPracticeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblQuestion = view.lblQuestionTitle
        val lblQuestionDescription = view.lblQuestionExtraDescription
        val lblOptionA = view.lblOptionA
        val lblOptionB = view.lblOptionB
        val lblOptionC = view.lblOptionC
        val lblOptionD = view.lblOptionD
        val btnSubmit = view.btnSubmit
    }

    companion object {
        const val QUESTION = 0
        const val NATIVE = 1
    }

}