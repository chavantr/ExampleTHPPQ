package com.passions.thehinduquestions

data class QuestionModel(val id: String,
                         val questionTitle: String,
                         val questionDescription: String,
                         val optionA: String,
                         val optionB: String, val optionC:
                         String, val optionD: String,
                         val correctAnswer: String,
                         val fullDescription: String,
                         val type: String,
                         val viewAnswer: Boolean = false,
                         var yourAnswer: String = "",
                         var submitted: Boolean = false)