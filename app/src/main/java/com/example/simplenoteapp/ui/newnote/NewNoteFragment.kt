@file:Suppress("DEPRECATION")

package com.example.simplenoteapp.ui.newnote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.simplenoteapp.R
import com.example.simplenoteapp.databinding.FragmentNewNoteBinding
import com.example.simplenoteapp.model.Note
import com.example.simplenoteapp.toast
import com.example.simplenoteapp.ui.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

   private val noteViewModel: NoteViewModel by activityViewModels()

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

    private fun saveNote() {

        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteBody = binding.addNoteBody.text.toString().trim()

        if (noteTitle.isNotEmpty()) {
            noteViewModel.addNote(
                Note(0, noteTitle, noteBody)
            )
            activity?.toast("Please enter note title!")
            jumpToHome()
        } else {
            activity?.toast("Please enter note title!")
        }
    }
    private fun jumpToHome(){
        findNavController().popBackStack()
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_menu-> {
                saveNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.new_note_menu, menu)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}