package com.passions.thehinduquestions.binder

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.ads.Ad
import com.facebook.ads.NativeAd
import com.passions.thehinduquestions.PracticeQuestionDetailModel
import com.passions.thehinduquestions.R
import kotlinx.android.synthetic.main.native_new_item_row.view.*
import kotlinx.android.synthetic.main.practice_detail_row.view.*

class ResultDetailsAdForAdapter(question: ArrayList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var lstQuestion: ArrayList<Any> = question
    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): RecyclerView.ViewHolder {
        return when (itemType) {
            QUESTION -> ResultDetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.practice_detail_row, parent, false))
            NATIVE -> NativeAdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.native_new_item_row, parent, false))
            else -> ResultDetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.practice_detail_row, parent, false))
        }
    }

    override fun getItemCount(): Int = lstQuestion.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        val itemType = getItemViewType(position)

        if (itemType == QUESTION) {
            var questionViewHolder = viewHolder as ResultDetailsViewHolder
            var question = lstQuestion.get(position) as PracticeQuestionDetailModel
            questionViewHolder.lblQuestion.text = "${position.plus(1)}) " + question.question
            questionViewHolder.lblCorrectAnswer.text = "Correct Answer : " + question.correctAns.toUpperCase()
            questionViewHolder.lblYourAnswer.text = "Your Answer : " + question.yourAns.toUpperCase()
            if (!TextUtils.isEmpty(question.questionDetails)) {
                questionViewHolder.lblQuestionDescription.text = question.questionDetails.replace("\\n", "")
                questionViewHolder.lblQuestionDescription.visibility = View.VISIBLE
            } else {
                questionViewHolder.lblQuestionDescription.text = ""
                questionViewHolder.lblQuestionDescription.visibility = View.GONE
            }
            questionViewHolder.lblOptionA.text = "A) " + question.optionA
            questionViewHolder.lblOptionB.text = "B) " + question.optionB
            questionViewHolder.lblOptionC.text = "C) " + question.optionC
            questionViewHolder.lblOptionD.text = "D) " + question.optionD
        } else if (itemType == NATIVE) {
            val nativeAdViewHolder = viewHolder as NativeAdViewHolder
            var nativeAd = lstQuestion.get(position) as NativeAd
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
        val item = lstQuestion[position]
        return when (item) {
            is PracticeQuestionDetailModel -> QUESTION
            is Ad -> NATIVE
            else -> -1
        }
    }

    inner class ResultDetailsViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val lblQuestion = view!!.lblQuestion
        val lblCorrectAnswer = view!!.lblCorrectAnswer
        val lblYourAnswer = view!!.lblYourAnswer
        val lblQuestionDescription = view!!.lblQuestionExtraDescription
        val lblOptionA = view!!.lblOptionA
        val lblOptionB = view!!.lblOptionB
        val lblOptionC = view!!.lblOptionC
        val lblOptionD = view!!.lblOptionD
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

    companion object {
        const val QUESTION = 0
        const val NATIVE = 1
    }
}