package com.passions.thehinduquestions

import java.util.*

data class PracticeQuestionPaperMasterModel(var id: Int=0, var date: String = Calendar.getInstance().timeInMillis.toString(),
                                            var name: String = "Question Paper",
                                            var totalQue: String = "0",
                                            var oqt: String = "0")