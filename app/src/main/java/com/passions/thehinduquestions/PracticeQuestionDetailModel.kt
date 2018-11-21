package com.passions.thehinduquestions

data class PracticeQuestionDetailModel(var id: Int = 0,
                                       var question: String = "",
                                       var questionDetails: String = "",
                                       var optionA: String = "",
                                       var optionB: String = "",
                                       var optionC: String = "",
                                       var optionD: String = "",
                                       var correctAns: String = "",
                                       var yourAns: String = "",
                                       var qid: Int = 0)