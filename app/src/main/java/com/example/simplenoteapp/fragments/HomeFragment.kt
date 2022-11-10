@file:Suppress("DEPRECATION")

package com.example.simplenoteapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.simplenoteapp.MainActivity
import com.example.simplenoteapp.R
import com.example.simplenoteapp.adapter.NoteAdapter
import com.example.simplenoteapp.databinding.FragmentHomeBinding
import com.example.simplenoteapp.db.NoteDataBase
import com.example.simplenoteapp.model.Note
import com.example.simplenoteapp.repository.NoteRepository
import com.example.simplenoteapp.viewmodel.NoteViewModel
import com.example.simplenoteapp.viewmodel.NoteViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel: NoteViewModel by activityViewModels() {
        NoteViewModelFactory(
            requireActivity().application, NoteRepository(
                NoteDataBase(requireContext())
            )
        )
    }
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )
        setUpRecyclerView()
        binding.fabAddNote.setOnClickListener { _ ->
            findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
        return binding.root
    }

    private fun setUpRecyclerView() {
        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        noteViewModel.getAllNote().observe(viewLifecycleOwner) { note ->
            noteAdapter.differ.submitList(note)
            updateUI(note)
        }

    }


    private fun updateUI(note: List<Note>) {
        if (note.isNotEmpty()) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.tvNoNotes.visibility = View.GONE
        } else {
            binding.recyclerView.visibility = View.GONE
            binding.tvNoNotes.visibility = View.VISIBLE
        }

    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
