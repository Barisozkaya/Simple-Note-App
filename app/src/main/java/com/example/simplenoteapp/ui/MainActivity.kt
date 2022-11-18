package com.example.simplenoteapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.simplenoteapp.databinding.ActivityMainBinding
import com.example.simplenoteapp.db.NoteDataBase
import com.example.simplenoteapp.repository.NoteRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val noteViewModel: NoteViewModel by viewModels() {
        NoteViewModelFactory(application, NoteRepository(
            NoteDataBase(this)
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setUpViewModel()

    }

    private fun setUpViewModel(){

    }
}