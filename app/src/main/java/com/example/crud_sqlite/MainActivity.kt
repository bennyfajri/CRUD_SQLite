package com.example.crud_sqlite

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var myDB: MyDatabaseHelper
    lateinit var book_id: ArrayList<String>
    lateinit var book_title:ArrayList<String>
    lateinit var book_author:ArrayList<String>
    lateinit var book_page:ArrayList<String>
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(applicationContext, AddActivity::class.java)
            startActivity(intent)
        }

        myDB = MyDatabaseHelper(this@MainActivity)
        book_id = ArrayList()
        book_title = ArrayList()
        book_author = ArrayList()
        book_page = ArrayList()

        storeDataInArrays()

        customAdapter = CustomAdapter(this@MainActivity,applicationContext)
        customAdapter.setData(book_id, book_title, book_author, book_page)
        binding.recyclerView.adapter = customAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            recreate()
        }
    }

    fun storeDataInArrays() {
        val cursor: Cursor? = myDB.readAllData()
        if(cursor?.count == 0){
            Toast.makeText(this@MainActivity, "No data.", Toast.LENGTH_SHORT).show()
        }else{
            while (cursor!!.moveToNext()){
                book_id.add(cursor.getString(0))
                book_title.add(cursor.getString(1))
                book_author.add(cursor.getString(2))
                book_page.add(cursor.getString(3))
            }
        }
    }
}