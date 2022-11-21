package com.example.simplenoteapp.ui.updatenote

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Delete
import com.example.simplenoteapp.R
import com.example.simplenoteapp.databinding.FragmentUpdateNoteBinding
import com.example.simplenoteapp.model.Note
import com.example.simplenoteapp.toast
import com.example.simplenoteapp.ui.MainActivity
import com.example.simplenoteapp.ui.NoteViewModel

@Suppress("DEPRECATION")
class UpdateNoteFragment : Fragment() {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateNoteBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.addNoteTitleUpdate.setText(currentNote.nateTitle)
        binding.addNoteBodyUpdate.setText(currentNote.noteBody)
        fabUpdateListeners()
    }

    private fun fabUpdateListeners() {
        binding.fabUpdate.setOnClickListener {
            val titte = binding.addNoteTitleUpdate.text.toString().trim()
            val body = binding.addNoteBodyUpdate.text.toString().trim()

            if (titte.isNotEmpty()) {
                val note = Note(currentNote.id, titte, body)
                noteViewModel.updateNote(note)

                activity?.toast("Note Updated!")
                view?.findNavController()?.navigate(
                    R.id.action_updateNoteFragment_to_homeFragment
                )
            } else {
                activity?.toast("Please enter title name")
            }
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you sure want to delete")
            setPositiveButton("DELETE") { _, _ ->
                noteViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(
                    R.id.action_updateNoteFragment_to_homeFragment
                )
            }

            setNegativeButton("Cancel", null)
        }.create().show()

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_menu -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
