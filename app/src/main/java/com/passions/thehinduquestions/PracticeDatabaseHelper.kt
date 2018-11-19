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


    fun getSolvedMasterPapers(): Array<PracticeQuestionPaperMasterModel>? {

        val db = readableDatabase

        var cursor = db.query("QUESTION_PAPER_MASTER", null, null, null, null, null, null)

        if (cursor.moveToFirst()) {

            var solvedQuestion : Array<PracticeQuestionPaperMasterModel>

            do {

                var practiceQuestionMaster = PracticeQuestionPaperMasterModel()



            } while (cursor.moveToNext())

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