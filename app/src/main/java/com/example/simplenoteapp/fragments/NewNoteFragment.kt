@file:Suppress("DEPRECATION")

package com.example.simplenoteapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.simplenoteapp.R
import com.example.simplenoteapp.databinding.FragmentHomeBinding
import com.example.simplenoteapp.databinding.FragmentNewNoteBinding


class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private var _binding: FragmentNewNoteBinding? = null
    private  val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewNoteBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.new_note_menu,menu)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}