package com.gmail.tunnisafitra.workshopresponsi

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView


class LihatBiodata : AppCompatActivity() {
    protected lateinit var cursor: Cursor
    internal lateinit var dbHelper: DataHelper
    internal lateinit var ton2: Button
    internal lateinit var text1: TextView
    internal lateinit var text2: TextView
    internal lateinit var text3: TextView
    internal lateinit var text4: TextView
    internal lateinit var text5: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_biodata)
        dbHelper = DataHelper(this)
        text1 = findViewById<View>(R.id.textView1) as TextView
        text2 = findViewById<View>(R.id.textView2) as TextView
        text3 = findViewById<View>(R.id.textView3) as TextView
        text4 = findViewById<View>(R.id.textView4) as TextView
        text5 = findViewById<View>(R.id.textView5) as TextView
        val db = dbHelper.readableDatabase
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                intent.getStringExtra("nama") + "'", null)
        cursor.moveToFirst()
        if (cursor.count > 0) {
            cursor.moveToPosition(0)
            text1.text = cursor.getString(0).toString()
            text2.text = cursor.getString(1).toString()
            text3.text = cursor.getString(2).toString()
            text4.text = cursor.getString(3).toString()
            text5.text = cursor.getString(4).toString()
        }
        ton2 = findViewById<View>(R.id.button1) as Button
        ton2.setOnClickListener {
            // TODO Auto-generated method stub
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}
