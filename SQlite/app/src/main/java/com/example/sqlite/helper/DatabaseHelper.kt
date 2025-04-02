package com.example.sqlite.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "ContactDB", null, 1) {

    companion object {
        const val TABLE_NAME = "contacts"
        const val COL_NAME = "name"
        const val COL_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val cretaTable = "CREATE TABLE $TABLE_NAME ($COL_NAME TEXT, $COL_PHONE TEXT)"
        db?.execSQL(cretaTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val creaTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(creaTable)
    }

    fun insertData(name: String, phone: String): Long {
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, name)
        contentValues.put(COL_PHONE, phone)
        val db = this.writableDatabase
        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun updateData(name: String, phone: String): Int {
        val contentValues = ContentValues()
        contentValues.put(COL_PHONE, phone)
        val db = this.writableDatabase
        return db.update(TABLE_NAME, contentValues, "$COL_NAME = ?", arrayOf(name))
    }

    fun deleteData(name: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COL_NAME = ?", arrayOf(name))
    }

    fun getAllData(): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val result = StringBuilder()
        while (cursor.moveToNext()) {
            val name = cursor.getString(0)
            val phone = cursor.getString(1)
            result.append("Name: $name\nPhone: $phone\n\n")
        }
        cursor.close()
        return result.toString()
    }


    }
