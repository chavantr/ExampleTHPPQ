package com.passions.thehinduquestions

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PracticeDatabaseHelper(context: Context?, name: String?,
                             factory: SQLiteDatabase.CursorFactory?,
                             version: Int, errorHandler: DatabaseErrorHandler?)
    : SQLiteOpenHelper(context, name, factory, version, errorHandler) {


    private val CREATE_MASTER_PRACTICE_QUESTION = "CREATE TABLE IF NOT EXISTS QUESTION_PAPER_MASTER (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, NAME TEXT, NOQ TEXT, QOT TEXT);"

    private val CREATE_DETAIL_PRACTICE_QUESTION = "CREATE TABLE IF NOT EXISTS QUESTION_PAPER_DETAIL (ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION TEXT, QUE_DES, TEXT, OPTION_A TEXT, OPTION_B TEXT,OPTION_C TEXT,OPTION_D TEXT,CORRECT_ANS TEXT, YOUR_ANS TEXT, Q_ID INTEGER);"


    override fun onCreate(database: SQLiteDatabase?) {
        database!!.execSQL(CREATE_MASTER_PRACTICE_QUESTION)
        database!!.execSQL(CREATE_DETAIL_PRACTICE_QUESTION)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun createMasterQuestionPaper(practiceQuestionMaster: PracticeQuestionPaperMasterModel): Int {
        val db = writableDatabase
        var contentValues = ContentValues()
        contentValues.put("DATE", practiceQuestionMaster.date)
        contentValues.put("NAME", practiceQuestionMaster.name)
        contentValues.put("NOQ", practiceQuestionMaster.totalQue)
        contentValues.put("QOT", practiceQuestionMaster.oqt)
        val inserted = db.insert("QUESTION_PAPER_MASTER", null, contentValues)
        if (inserted > 0) {
            return getLastInserted()
        }
        return 0;
    }

    fun createQuestionDetail(questionDetail: List<PracticeQuestionDetailModel>): Long {
        val db = writableDatabase
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
            count += db.insert("QUESTION_PAPER_DETAIL", null, contentValues)
        }
        return count
    }

    fun updateQuestionAnswer(practiceQuestionDetail: QuestionModel): Int {
        val db = writableDatabase
        var contentValues = ContentValues()
        contentValues.put("YOUR_ANS", practiceQuestionDetail.yourAnswer)
        print("ID"+ practiceQuestionDetail.id + "Your Answer" + practiceQuestionDetail.yourAnswer)
        val args = arrayOf(practiceQuestionDetail.id)
        return db.update("QUESTION_PAPER_DETAIL", contentValues, "ID=?", args)
    }

    fun getQuestionDetails(id: Int): List<PracticeQuestionDetailModel>? {
        val db = readableDatabase
        val args = arrayOf(id.toString())
        var cursor = db.query("QUESTION_PAPER_DETAIL", null, "Q_ID=?", args, null, null, null)
        if (cursor.moveToFirst()) {
            var questionDetail = ArrayList<PracticeQuestionDetailModel>()
            do {
                var questionDetailModel = PracticeQuestionDetailModel()
                questionDetailModel.id = cursor.getInt(cursor.getColumnIndex("ID"))
                questionDetailModel.question = cursor.getString(cursor.getColumnIndex("QUESTION"))
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

    fun getSolvedMasterPapers(): List<PracticeQuestionPaperMasterModel>? {
        val db = readableDatabase
        var cursor = db.query("QUESTION_PAPER_MASTER", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            var solvedQuestion = ArrayList<PracticeQuestionPaperMasterModel>()
            do {
                var practiceQuestionMaster = PracticeQuestionPaperMasterModel()
                practiceQuestionMaster.id = cursor.getInt(cursor.getColumnIndex("ID"))
                practiceQuestionMaster.name = cursor.getString(cursor.getColumnIndex("NAME"))
                practiceQuestionMaster.date = cursor.getString(cursor.getColumnIndex("DATE"))
                practiceQuestionMaster.totalQue = cursor.getString(cursor.getColumnIndex("NOQ"))
                practiceQuestionMaster.oqt = cursor.getString(cursor.getColumnIndex("QOT"))
                solvedQuestion.add(practiceQuestionMaster)
            } while (cursor.moveToNext())
            return solvedQuestion
        }
        return null
    }

    private fun getLastInserted(): Int {
        val db = readableDatabase
        var lastId = 0
        val query = "SELECT ID from QUESTION_PAPER_MASTER order by ID DESC limit 1"
        val c = db.rawQuery(query, null)
        if (c != null && c.moveToFirst()) {
            lastId = c.getLong(0).toInt()
        }
        return lastId + 1
    }
}