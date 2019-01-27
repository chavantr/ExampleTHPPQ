package com.passions.thehinduquestions.binder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.ads.Ad
import com.facebook.ads.NativeAd
import com.passions.thehinduquestions.PracticeQuestionPaperMasterModel
import com.passions.thehinduquestions.R
import com.passions.thehinduquestions.ResultActivity
import kotlinx.android.synthetic.main.layout_question_list.view.*
import kotlinx.android.synthetic.main.native_new_item_row.view.*
import java.text.SimpleDateFormat
import java.util.*

class QuestionPaperAdForAdapter(question: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var lstQuestions: List<Any> = question

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): RecyclerView.ViewHolder {
        return when (itemType) {
            QUESTION -> QuestionPaperViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_question_list, parent, false))
            NATIVE -> NativeAdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.native_new_item_row, parent, false))
            else -> QuestionPaperViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_question_list, parent, false))
        }
    }

    override fun getItemCount(): Int = lstQuestions.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val itemType = getItemViewType(position)
        if (itemType == QUESTION) {
            val questionPaper = lstQuestions.get(position) as PracticeQuestionPaperMasterModel
            var questionPaperViewHolder = viewHolder as QuestionPaperViewHolder
            questionPaperViewHolder.lblName.text = questionPaper.name + " " + position.plus(1)
            questionPaperViewHolder.lblDate.text = convertMilliSecondToDate(questionPaper.date.toLong())
            questionPaperViewHolder.llContainer.setOnClickListener {
                val intent = Intent(it.context, ResultActivity::class.java)
                intent.putExtra("id", questionPaper.id)
                it.context.startActivity(intent)
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

    private fun convertMilliSecondToDate(date: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm a")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        return "Date : " + formatter.format(calendar.time)
    }

    inner class QuestionPaperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var lblName = view.lblName
        var lblDate = view.lblDate
        var llContainer = view.llContainer
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

    override fun getItemViewType(position: Int): Int {
        val item = lstQuestions[position];
        return when (item) {
            is PracticeQuestionPaperMasterModel -> QUESTION
            is Ad -> NATIVE
            else -> -1
        }
    }

    companion object {
        const val QUESTION = 0
        const val NATIVE = 1
    }

}