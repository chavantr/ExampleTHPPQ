package com.passions.thehinduquestions.binder

import android.content.Intent
import android.speech.tts.TextToSpeech
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.ads.Ad
import com.facebook.ads.NativeAd
import com.passions.thehinduquestions.QuestionDetailsScrollActivity
import com.passions.thehinduquestions.QuestionModel
import com.passions.thehinduquestions.R
import kotlinx.android.synthetic.main.daily_questions_list.view.*
import kotlinx.android.synthetic.main.native_new_item_row.view.*
import java.util.*


class DailyQuestionAdAdapter(questions: List<Any>, textToSpeech: TextToSpeech?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var lstQuestion: List<Any> = questions
    var textToSpeech = textToSpeech

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            QUESTION -> QuestionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.daily_questions_list, parent, false))
            NATIVE -> NativeAdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.native_new_item_row, parent, false))
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

            recipeViewHolder.btnListen.setOnClickListener {
                generate(questions)
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
            is QuestionModel -> QUESTION
            is Ad -> NATIVE
            else -> -1
        }
    }

    companion object {
        const val QUESTION = 0
        const val NATIVE = 1
        const val DELAY = 999L
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
        val btnListen = view.btnListen
        val lblCorrectAnswer = view.lblCorrectAnswer

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

    private fun generate(questionModel: QuestionModel?) {
        val myHash = HashMap<String, String>()
        myHash[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "done"

        textToSpeech!!.speak(
                "Question ${questionModel!!.questionTitle}",
                TextToSpeech.QUEUE_FLUSH,
                myHash
        )
        delayIn()

        if (questionModel!!.questionDescription.isNotEmpty()) {
            textToSpeech!!.speak(
                    "${questionModel!!.questionDescription}",
                    TextToSpeech.QUEUE_ADD,
                    myHash
            )
            delayIn()
        }


        textToSpeech!!.speak(
                "A",
                TextToSpeech.QUEUE_ADD,
                myHash
        )
        delayIn()
        textToSpeech!!.speak(
                "A ${questionModel!!.optionA}",
                TextToSpeech.QUEUE_ADD,
                myHash
        )

        delayIn()
        textToSpeech!!.speak(
                "B",
                TextToSpeech.QUEUE_ADD,
                myHash
        )
        delayIn()
        textToSpeech!!.speak(
                "B ${questionModel!!.optionB}",
                TextToSpeech.QUEUE_ADD,
                myHash
        )

        delayIn()
        textToSpeech!!.speak(
                "C",
                TextToSpeech.QUEUE_ADD,
                myHash
        )
        delayIn()
        textToSpeech!!.speak(
                "C ${questionModel!!.optionC}",
                TextToSpeech.QUEUE_ADD,
                myHash
        )

        delayIn()

        textToSpeech!!.speak(
                "D",
                TextToSpeech.QUEUE_ADD,
                myHash
        )
        delayIn()
        textToSpeech!!.speak(
                "D ${questionModel!!.optionD}",
                TextToSpeech.QUEUE_ADD,
                myHash
        )

        delayIn()

        textToSpeech!!.speak(
                "Correct Answer is ${questionModel!!.correctAnswer}",
                TextToSpeech.QUEUE_ADD,
                myHash
        )

    }

    private fun delayIn() {
        textToSpeech!!.playSilence(DELAY, TextToSpeech.QUEUE_ADD, null)
    }


}