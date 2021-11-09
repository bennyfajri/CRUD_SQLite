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

    }
}