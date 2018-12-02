package com.passions.thehinduquestions.binder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.ads.Ad
import com.facebook.ads.AdChoicesView
import com.facebook.ads.NativeAd
import com.passions.thehinduquestions.QuestionDetailsScrollActivity
import com.passions.thehinduquestions.QuestionModel
import com.passions.thehinduquestions.R
import kotlinx.android.synthetic.main.daily_questions_list.view.*
import kotlinx.android.synthetic.main.item_native_ad.view.*


class DailyQuestionAdAdapter(questions: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var lstQuestion: List<Any> = questions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            QUESTION -> QuestionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.daily_questions_list, parent, false))
            NATIVE -> NativeAdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_native_ad, parent, false))
            else -> QuestionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.daily_questions_list, parent, false))
        }
    }

    override fun getItemCount(): Int = lstQuestion.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val itemType = getItemViewType(position)
        if (itemType == QUESTION) {
            var questions = lstQuestion.get(position) as QuestionModel
            val recipeViewHolder = viewHolder as QuestionViewHolder
            recipeViewHolder.bntViewAnsswer.setOnClickListener {
                recipeViewHolder.lblCorrectAnswer.visibility = View.VISIBLE
                recipeViewHolder.btnShowDetails.visibility = View.VISIBLE
                questions.viewAnswer = true
            }
            recipeViewHolder.btnShowDetails.setOnClickListener { view ->
                val intent = Intent(view.context, QuestionDetailsScrollActivity::class.java)
                intent.putExtra("extra", questions.fullDescription)
                intent.putExtra("type", questions.type)
                view.context.startActivity(intent)
            }
            if (questions.viewAnswer) {
                recipeViewHolder.lblCorrectAnswer.visibility = View.VISIBLE
                recipeViewHolder.btnShowDetails.visibility = View.VISIBLE
            } else {
                recipeViewHolder.lblCorrectAnswer.visibility = View.GONE
                recipeViewHolder.btnShowDetails.visibility = View.GONE
            }
            recipeViewHolder.lblQuestion.text = "${position.plus(1)}) " + questions.questionTitle
            if (!TextUtils.isEmpty(questions.questionDescription)) {
                recipeViewHolder.lblQuestionDescription.text = questions.questionDescription.replace("\\n", "")
                recipeViewHolder.lblQuestionDescription.visibility = View.VISIBLE
            } else {
                recipeViewHolder.lblQuestionDescription.text = ""
                recipeViewHolder.lblQuestionDescription.visibility = View.GONE
            }
            recipeViewHolder.lblOptionA.text = "A) " + questions.optionA
            recipeViewHolder.lblOptionB.text = "B) " + questions.optionB
            recipeViewHolder.lblOptionC.text = "C) " + questions.optionC
            recipeViewHolder.lblOptionD.text = "D) " + questions.optionD
            recipeViewHolder.lblCorrectAnswer.text = questions.correctAnswer.toUpperCase()
        } else if (itemType == NATIVE) {
            val nativeAdViewHolder = viewHolder as NativeAdViewHolder
            var nativeAd = lstQuestion.get(position) as NativeAd
            nativeAdViewHolder.tvAdTitle.text = nativeAd.advertiserName
            nativeAdViewHolder.tvAdBody.text = nativeAd.adBodyText
            nativeAdViewHolder.btnCTA.text = nativeAd.adCallToAction
            nativeAdViewHolder.sponsorLabel.text = nativeAd.sponsoredTranslation
            nativeAdViewHolder.adChoicesContainer.removeAllViews()
            val adChoicesView = AdChoicesView(nativeAdViewHolder.containder.context, nativeAd, true)
            nativeAdViewHolder.adChoicesContainer.addView(adChoicesView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = lstQuestion.get(position)
        return when (item) {
            is QuestionModel -> QUESTION
            is Ad -> NATIVE
            else -> -1
        }
    }

    companion object {
        const val QUESTION = 0
        const val NATIVE = 1
    }

    inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val lblQuestion = view.lblQuestionTitle
        val lblQuestionDescription = view.lblQuestionExtraDescription
        val lblOptionA = view.lblOptionA
        val lblOptionB = view.lblOptionB
        val lblOptionC = view.lblOptionC
        val lblOptionD = view.lblOptionD
        val bntViewAnsswer = view.btnShowAnswer
        val btnShowDetails = view.btnViewDescription
        val lblCorrectAnswer = view.lblCorrectAnswer

    }

    inner class NativeAdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val containder = view
        val adIconView = view.adIconView
        val tvAdTitle = view.tvAdTitle
        val tvAdBody = view.tvAdBody
        val btnCTA = view.btnCTA
        val adChoicesContainer = view.adChoicesContainer
        val mediaView = view.mediaView
        val sponsorLabel = view.sponsored_label
    }
}