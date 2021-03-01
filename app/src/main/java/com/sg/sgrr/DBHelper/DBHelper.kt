package com.sg.sgrr.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sg.sgrr.Model.Record

class DBHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VAR) {
    companion object {
        private const val DATABASE_VAR = 1
        private const val DATABASE_NAME = "Black.db"

        private const val TABLE_NAME = "Record"
        private const val COOL_ID = "Id"
        private const val COOL_NICKNAME = "NickName"
        private const val COOL_RANK = "Rank"
        private const val COOL_RANK_NUMBER = "RankNumber"
        private const val COOL_RANK_PERCENT = "RankPercent"
        private const val COOL_MOST_IMAGE_1 = "MostImage1"
        private const val COOL_MOST_IMAGE_2 = "MostImage2"
        private const val COOL_MOST_IMAGE_3 = "MostImage3"
        private const val COOL_MOST_PERCENT_1 = "MostPercent1"
        private const val COOL_MOST_PERCENT_2 = "MostPercent2"
        private const val COOL_MOST_PERCENT_3 = "MostPercent3"
        private const val COOL_USER_NUMBER = "UserNumber"
        private const val SQL_CREATE_ENTRIES =
                "CREATE TABLE $TABLE_NAME (" +
                        "$COOL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "$COOL_NICKNAME TEXT," +
                        "$COOL_RANK TEXT, " +
                        "$COOL_RANK_NUMBER TEXT, " +
                        "$COOL_RANK_PERCENT TEXT, " +
                        "$COOL_MOST_IMAGE_1 INTEGER, " +
                        "$COOL_MOST_IMAGE_2 INTEGER, " +
                        "$COOL_MOST_IMAGE_3 INTEGER, " +
                        "$COOL_MOST_PERCENT_1 TEXT, " +
                        "$COOL_MOST_PERCENT_2 TEXT, " +
                        "$COOL_MOST_PERCENT_3 TEXT, " +
                        "$COOL_USER_NUMBER INTEGER " +
                        " )"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addRecord(record: Record){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COOL_ID, record.id)
            put(COOL_NICKNAME, record.userName)
            put(COOL_RANK, record.rank)
            put(COOL_RANK_NUMBER, record.rankNumber)
            put(COOL_RANK_PERCENT, record.rankPercent)
            put(COOL_MOST_IMAGE_1, record.mostImage1)
            put(COOL_MOST_IMAGE_2, record.mostImage2)
            put(COOL_MOST_IMAGE_3, record.mostImage3)
            put(COOL_MOST_PERCENT_1, record.mostPercent1)
            put(COOL_MOST_PERCENT_2, record.mostPercent2)
            put(COOL_MOST_PERCENT_3, record.mostPercent3)
            put(COOL_USER_NUMBER, record.userId)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateRecord(nickName: String, record: Record){
        val db = this.writableDatabase
        val update = "UPDATE $TABLE_NAME SET $COOL_RANK = '${record.rank}'," +
                "$COOL_RANK_NUMBER = '${record.rankNumber}', " +
                "$COOL_RANK_PERCENT = '${record.rankPercent}', " +
                "$COOL_MOST_IMAGE_1 = '${record.mostImage1}', " +
                "$COOL_MOST_IMAGE_2 = '${record.mostImage2}', " +
                "$COOL_MOST_IMAGE_3 = '${record.mostImage3}', " +
                "$COOL_MOST_PERCENT_1 = '${record.mostPercent1}', " +
                "$COOL_MOST_PERCENT_2 = '${record.mostPercent2}', " +
                "$COOL_MOST_PERCENT_3 = '${record.mostPercent3}' " +
                "WHERE $COOL_NICKNAME = '$nickName'"
        db.execSQL(update)
        db.close()
    }

    fun getUserNumber(nickName: String): Int {
        val db = this.readableDatabase
        val sql = "SELECT $COOL_USER_NUMBER FROM $TABLE_NAME WHERE $COOL_NICKNAME = '$nickName'"
        val cursor: Cursor = db.rawQuery(sql, null)
        var userNumber = 0

        if(cursor.count > 0){
            if(cursor.moveToFirst()){
                userNumber = cursor.getInt( cursor.getColumnIndex(COOL_USER_NUMBER) )
            }
        }

        db.close()

        return userNumber
    }

    fun getRecord(nickName: String): Int {
        val db = this.readableDatabase
        val sql = "SELECT $COOL_NICKNAME FROM $TABLE_NAME WHERE $COOL_NICKNAME = '$nickName'"
        val cursor: Cursor = db.rawQuery(sql, null)

        val returnValue = cursor.count
        db.close()
        return returnValue
    }

    fun getAllRecord(): ArrayList<Record> {
        val db = this.readableDatabase
        val sql = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(sql, null)

        val lists = ArrayList<Record>()
        while (cursor.moveToNext()){
            lists.add(Record(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11)
            ).apply {
                id = cursor.getInt(0) })
        }
        db.close()
        return lists
    }

    fun deleteRecord(nickName: String){
        val db = this.writableDatabase
        val update = "DELETE FROM $TABLE_NAME " +
                "WHERE $COOL_NICKNAME = '$nickName'"
        db.execSQL(update)
        db.close()
    }
}