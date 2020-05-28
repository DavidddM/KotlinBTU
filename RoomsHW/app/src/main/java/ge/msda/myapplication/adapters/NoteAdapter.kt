package ge.msda.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.msda.myapplication.R
import ge.msda.myapplication.models.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(
    private var notes: MutableList<Note>
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.itemView.tView.text = note.note
    }

    fun updateNotes(notes: MutableList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    fun addNote(note: Note) {
        this.notes.add(note)
        notifyDataSetChanged()
    }
}