package com.passions.thehinduquestions

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class DatabaseFunctions(db:SQLiteDatabase) {

    var myDb :SQLiteDatabase = db

   fun createMasterQuestionPaper(practiceQuestionMaster: PracticeQuestionPaperMasterModel): Int {
        val db = myDb
        var contentValues = ContentValues()
        contentValues.put("DATE", practiceQuestionMaster.date)
        contentValues.put("NAME", practiceQuestionMaster.name)
        contentValues.put("NOQ", practiceQuestionMaster.totalQue)
        contentValues.put("QOT", practiceQuestionMaster.oqt)
        val inserted = db.insert("QUESTION_PAPER_MASTER", null, contentValues)
        if (inserted > 0) {
            return getLastInserted()
        }
        return 1;
    }

    fun createQuestionDetail(questionDetail: List<PracticeQuestionDetailModel>): Long {
        val db = myDb
        var count: Long = 0
        for (i in questionDetail.indices) {
            var contentValues = ContentValues()
            contentValues.put("QUESTION", questionDetail.get(i).question)
            contentValues.put("QUE_DES", questionDetail.get(i).questionDetails)
            contentValues.put("OPTION_A", questionDetail.get(i).optionA)
            contentValues.put("OPTION_B", questionDetail.get(i).optionB)
            contentValues.put("OPTION_C", questionDetail.get(i).optionC)
            contentValues.put("OPTION_D", questionDetail.get(i).optionD)
            contentValues.put("CORRECT_ANS", questionDetail.get(i).correctAns)
            contentValues.put("YOUR_ANS", questionDetail.get(i).yourAns)
            contentValues.put("Q_ID", questionDetail.get(i).qid)
            contentValues.put("REF_ID", questionDetail.get(i).id)
            count += db.insert("QUESTION_PAPER_DETAIL", null, contentValues)
        }
        return count
    }

    fun updateQuestionAnswer(practiceQuestionDetail: PracticeQuestionDetailModel): Int {
        val db = myDb
        var contentValues = ContentValues()
        contentValues.put("YOUR_ANS", practiceQuestionDetail.yourAns)
        val args = arrayOf(practiceQuestionDetail.id.toString(), practiceQuestionDetail.qid.toString())
        return db.update("QUESTION_PAPER_DETAIL", contentValues, "REF_ID=? AND Q_ID=?", args)
    }

    fun getQuestionDetails(id: Int): ArrayList<Any>? {
        val db = myDb
        val args = arrayOf(id.plus(1).toString())
        var cursor = db.query("QUESTION_PAPER_DETAIL", null, "Q_ID=?", args, null, null, null)
        if (cursor.moveToFirst()) {
            var questionDetail = ArrayList<Any>()
            do {
                var questionDetailModel = PracticeQuestionDetailModel()
                questionDetailModel.id = cursor.getInt(cursor.getColumnIndex("ID"))
                questionDetailModel.question = cursor.getString(cursor.getColumnIndex("QUESTION"))
                questionDetailModel.questionDetails = cursor.getString(cursor.getColumnIndex("QUE_DES"))
                questionDetailModel.optionA = cursor.getString(cursor.getColumnIndex("OPTION_A"))
                questionDetailModel.optionB = cursor.getString(cursor.getColumnIndex("OPTION_B"))
                questionDetailModel.optionC = cursor.getString(cursor.getColumnIndex("OPTION_C"))
                questionDetailModel.optionD = cursor.getString(cursor.getColumnIndex("OPTION_D"))
                questionDetailModel.correctAns = cursor.getString(cursor.getColumnIndex("CORRECT_ANS"))
                questionDetailModel.yourAns = cursor.getString(cursor.getColumnIndex("YOUR_ANS"))
                questionDetailModel.qid = cursor.getInt(cursor.getColumnIndex("Q_ID"))
                questionDetail.add(questionDetailModel)

            } while (cursor.moveToNext())

            return questionDetail
        }
        return null
    }

    fun getSolvedMasterPapers(): ArrayList<Any>? {
        val db = myDb
        var solvedQuestion = ArrayList<Any>()
        var cursor = db.query("QUESTION_PAPER_MASTER", null, null, null, null, null, "DATE DESC")
        if (cursor.moveToFirst()) {
            var solvedQuestion = ArrayList<Any>()
            do {
                var practiceQuestionMaster = PracticeQuestionPaperMasterModel()
                practiceQuestionMaster.id = cursor.getInt(cursor.getColumnIndex("ID"))
                practiceQuestionMaster.name = cursor.getString(cursor.getColumnIndex("NAME"))
                practiceQuestionMaster.date = cursor.getString(cursor.getColumnIndex("DATE"))
                practiceQuestionMaster.totalQue = cursor.getString(cursor.getColumnIndex("NOQ"))
                practiceQuestionMaster.oqt = cursor.getString(cursor.getColumnIndex("QOT"))
                solvedQuestion.add(practiceQuestionMaster)
            } while (cursor.moveToNext())
            //return solvedQuestion
        }
        return solvedQuestion
    }

    private fun getLastInserted(): Int {
        val db = myDb
        var lastId = 0
        val query = "SELECT ID from QUESTION_PAPER_MASTER order by ID DESC limit 1"
        val c = db.rawQuery(query, null)
        if (c != null && c.moveToFirst()) {
            lastId = c.getLong(0).toInt()
        }
        return lastId + 1
    }


}