package com.passions.thehinduquestions

import java.util.*

data class PracticeQuestionPaperMasterModel(val date: String = Calendar.getInstance().timeInMillis.toString(),
                                            val name: String = "Question Paper",
                                            val totalQue: String = "0",
                                            var oqt: String = "0")