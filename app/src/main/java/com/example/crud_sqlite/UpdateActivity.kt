package com.example.crud_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crud_sqlite.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateBinding
    lateinit var id: String
    lateinit var title: String
    lateinit var author: String
    lateinit var pages: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAndSetIntentData()
        binding.btnUpdate.setOnClickListener {

            val myDB = MyDatabaseHelper(this@UpdateActivity)
            title = binding.etTitle.text.trim().toString()
            author = binding.etAuthor.text.trim().toString()
            pages = binding.etPages.text.trim().toString()
            myDB.updateData(id, title, author, pages)
        }

    }

    fun getAndSetIntentData() {
        if (
            intent.hasExtra("id") && intent.hasExtra("title") &&
            intent.hasExtra("author") && intent.hasExtra("pages")
        ) {
            // Getting Data from Intent
            id = intent.getStringExtra("id").toString()
            title = intent.getStringExtra("title").toString()
            author = intent.getStringExtra("author").toString()
            pages = intent.getStringExtra("pages").toString()

            //Setting Intent Data
            binding.etTitle.setText(title)
            binding.etAuthor.setText(author)
            binding.etPages.setText(pages)

        } else {
            Toast.makeText(applicationContext, "No data.", Toast.LENGTH_SHORT).show()
        }
    }
}