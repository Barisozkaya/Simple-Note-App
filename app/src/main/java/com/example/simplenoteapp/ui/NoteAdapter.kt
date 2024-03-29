package com.example.simplenoteapp.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenoteapp.databinding.NoteLayoutAdapterBinding
import com.example.simplenoteapp.model.Note
import com.example.simplenoteapp.ui.home.HomeFragmentDirections
import kotlin.random.Random

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    private var binding: NoteLayoutAdapterBinding? = null

    class NoteViewHolder(itemBinding: NoteLayoutAdapterBinding): RecyclerView.ViewHolder(itemBinding.root)

    private  val differCallBack =
        object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding = NoteLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return  NoteViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemView.apply {
            binding?.tvNoteTittle?.text = currentNote.nateTitle
            binding?.tvNoteBody?.text = currentNote.noteBody
            val random = java.util.Random()
            val color =Color.argb(
                255,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)

            )
            binding?.viewColor?.setBackgroundColor(color)
        }.setOnClickListener{ mView ->
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            mView.findNavController().navigate(
                direction
            )
        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}