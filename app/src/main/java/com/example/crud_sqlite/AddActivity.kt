package com.example.crud_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud_sqlite.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val myDB = MyDatabaseHelper(this@AddActivity)
            myDB.addBook(binding.etTitle.text.toString().trim(),
                binding.etAuthor.text.toString().trim(),
                binding.etPages.text.toString().trim().toInt())
        }
    }
}