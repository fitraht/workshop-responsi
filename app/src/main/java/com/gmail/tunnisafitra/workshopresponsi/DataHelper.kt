package com.gmail.tunnisafitra.workshopresponsi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DataHelper(context: Context)// TODO Auto-generated constructor stub
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // TODO Auto-generated method stub
        var sql = "create table biodata(no integer primary key, nama text null, tgl text null, jk text null, alamat text null);"
        Log.d("Data", "onCreate: $sql")
        db.execSQL(sql)
        sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES ('1001', 'Fathur', '1994-02-03', 'Laki-laki','Jakarta');"
        db.execSQL(sql)
    }

    override fun onUpgrade(arg0: SQLiteDatabase, arg1: Int, arg2: Int) {
        // TODO Auto-generated method stub
    }

    companion object {
        private val DATABASE_NAME = "biodatadiri.db"
        private val DATABASE_VERSION = 1
    }

}
