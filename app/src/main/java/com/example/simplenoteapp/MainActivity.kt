package com.example.simplenoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.simplenoteapp.databinding.ActivityMainBinding
import com.example.simplenoteapp.db.NoteDataBase
import com.example.simplenoteapp.repository.NoteRepository
import com.example.simplenoteapp.viewmodel.NoteViewModel
import com.example.simplenoteapp.viewmodel.NoteViewModelFactory
import com.example.simplenoteapp.viewmodel.NoteViewModelProviderFactory

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