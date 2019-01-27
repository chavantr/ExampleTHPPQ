
package com.passions.thehinduquestions;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.util.ArrayList;
import java.util.List;


public class MyDatabase extends SQLiteAssetHelper {

    public MyDatabase(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }


    public List<MonthNameModel> getMonthName() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("MonthCategory", null, "Year=?", new String[]{"2018"}, null, null, null);
        if (cursor.moveToFirst()) {
            List<MonthNameModel> monthName = new ArrayList<>();
            do {
                MonthNameModel monthNameModel = new MonthNameModel(
                        cursor.getInt(cursor.getColumnIndex("ID")),
                        cursor.getString(cursor.getColumnIndex("Year")),
                        cursor.getString(cursor.getColumnIndex("MonthName")));
                monthName.add(monthNameModel);
            } while (cursor.moveToNext());


            return monthName;
        }
        return null;
    }

    public ArrayList<Object> getQuestionMonthWise(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {String.valueOf(id)};
        Cursor cursor = db.query("QuestionMaster", null, "CategoryId=?", args, null, null, null);
        if (cursor.moveToFirst()) {
            ArrayList<Object> questionModels = new ArrayList<>();
            do {
                QuestionModel questionModel = new QuestionModel(String.valueOf(cursor.getInt(cursor.getColumnIndex("ID"))),
                        cursor.getString(cursor.getColumnIndex("Question")),
                        cursor.getString(cursor.getColumnIndex("QuestionDescription")),
                        cursor.getString(cursor.getColumnIndex("OptionA")),
                        cursor.getString(cursor.getColumnIndex("OptionB")),
                        cursor.getString(cursor.getColumnIndex("OptionC")),
                        cursor.getString(cursor.getColumnIndex("OptionD")),
                        cursor.getString(cursor.getColumnIndex("CorrectAnswer")),
                        cursor.getString(cursor.getColumnIndex("ExtraDescription")),
                        cursor.getString(cursor.getColumnIndex("Type")),
                        false, "",
                        false);
                questionModels.add(questionModel);
            } while (cursor.moveToNext());
            return questionModels;
        }
        return null;
    }

    public ArrayList<Object> getPracticeQuestion(int masterId) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM QuestionMaster ORDER BY RANDOM() LIMIT 20", null);
        if (cursor.moveToFirst()) {
            ArrayList<Object> questionModels = new ArrayList<>();
            do {
                PracticeQuestionDetailModel questionModel = new PracticeQuestionDetailModel(cursor.getInt(cursor.getColumnIndex("ID")),
                        cursor.getString(cursor.getColumnIndex("Question")),
                        cursor.getString(cursor.getColumnIndex("QuestionDescription")),
                        cursor.getString(cursor.getColumnIndex("OptionA")),
                        cursor.getString(cursor.getColumnIndex("OptionB")),
                        cursor.getString(cursor.getColumnIndex("OptionC")),
                        cursor.getString(cursor.getColumnIndex("OptionD")),
                        cursor.getString(cursor.getColumnIndex("CorrectAnswer")),
                        "",
                        masterId, false);
                questionModels.add(questionModel);
            } while (cursor.moveToNext());
            return questionModels;
        }
        return null;
    }



}
