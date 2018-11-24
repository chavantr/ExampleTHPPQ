package com.passions.thehinduquestions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_start_practice.*

class StartPracticeActivity : AppCompatActivity() {

    private lateinit var practiceDatabaseHelper: PracticeDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_practice)
        practiceDatabaseHelper = PracticeDatabaseHelper(this, "upscprelim", null, 1, null)
        var practiceQuestionPaperMasterModel = PracticeQuestionPaperMasterModel()
        val masterId = practiceDatabaseHelper.createMasterQuestionPaper(practiceQuestionPaperMasterModel)

        var practiceDetails = ArrayList<PracticeQuestionDetailModel>()


        for (i in questions.indices) {
            var practiceNode = PracticeQuestionDetailModel()
            practiceNode.question = questions.get(i).questionTitle
            practiceNode.questionDetails = questions.get(i).questionDescription
            practiceNode.optionA = questions.get(i).optionA
            practiceNode.optionB = questions.get(i).optionB
            practiceNode.optionC = questions.get(i).optionC
            practiceNode.optionD = questions.get(i).optionD
            practiceNode.correctAns = questions.get(i).correctAnswer
            practiceNode.yourAns = questions.get(i).yourAnswer
            practiceNode.qid = masterId
            practiceDetails.add(practiceNode)
        }

        practiceDatabaseHelper.createQuestionDetail(practiceDetails)

        lstPracticeQuestion.layoutManager = LinearLayoutManager(this)
        lstPracticeQuestion.adapter = StartPracticeAdapter(questions)
    }

    companion object {
        val questions = arrayOf(
                QuestionModel("", "Global Human Capital Index, recently in news, is published by", "", "World Bank", "World Economic Forum", "The International Economic Association",
                        "Economic Development Organisation", "b", "Self-explanatory", "International organisations"),

                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),

                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"), QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"),
                QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                        "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations"), QuestionModel("", "With reference to “the Inter-American Court of Human Rights (IACHR), recently in \n" +
                "news, which of the following statement(s) is/are correct?", "1) The IACHR is an independent, multinational court that handles the human rights cases of people affected by the laws of countries that are members of the Organisation of American States (OAS).\n2) It is a temporary body which monitors the general human rights and publishes country-specific human rights reports.\n Select the correct answer using the code given below:", "1 only", "2 only", "Both 1 and 2", "Neither 1 nor 1", "a", "IACHR is the permanent body which monitors general human rights only when necessary.", "International organisations")
        )
    }
}
