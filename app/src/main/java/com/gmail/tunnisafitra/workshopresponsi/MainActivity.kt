package com.gmail.tunnisafitra.workshopresponsi

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView


class MainActivity : AppCompatActivity() {
    internal lateinit var daftar: Array<String>
    internal lateinit var ListView01: ListView
    internal var menu: Menu? = null
    protected lateinit var cursor: Cursor
    internal lateinit var dbcenter: DataHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<View>(R.id.button2) as Button
        btn.setOnClickListener {
            // TODO Auto-generated method stub
            val inte = Intent(this@MainActivity, BuatBiodata::class.java)
            startActivity(inte)
        }
        var ma = this
        dbcenter = DataHelper(this)
        RefreshList()
    }

    fun RefreshList() {
        val db = dbcenter.readableDatabase
        cursor = db.rawQuery("SELECT * FROM biodata", null)
        
        cursor.moveToFirst()
        for (cc in 0 until cursor.count) {
            cursor.moveToPosition(cc)
            daftar[cc] = cursor.getString(1).toString()
        }
        ListView01 = findViewById<View>(R.id.listView1) as ListView
        ListView01.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar)
        ListView01.isSelected = true
        ListView01.onItemClickListener = OnItemClickListener { arg0, arg1, arg2, arg3 ->
            val selection = daftar[arg2] //.getItemAtPosition(arg2).toString();
            val dialogitem = arrayOf<CharSequence>("Lihat Biodata", "Update Biodata", "Hapus Biodata")
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Pilihan")
            builder.setItems(dialogitem) { dialog, item ->
                when (item) {
                    0 -> {
                        val i = Intent(applicationContext, LihatBiodata::class.java)
                        i.putExtra("nama", selection)
                        startActivity(i)
                    }
                    1 -> {
                        val `in` = Intent(applicationContext, UpdateBiodata::class.java)
                        `in`.putExtra("nama", selection)
                        startActivity(`in`)
                    }
                    2 -> {
                        val db = dbcenter.writableDatabase
                        db.execSQL("delete from biodata where nama = '$selection'")
                        RefreshList()
                    }
                }
            }
            builder.create().show()
        }
        (ListView01.adapter as ArrayAdapter<*>).notifyDataSetInvalidated()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


}
