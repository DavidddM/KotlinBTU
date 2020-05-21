package ge.msda.myapplication.adapters


import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.msda.myapplication.R
import ge.msda.myapplication.models.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(
    private var notes: MutableList<Note>,
    private val sharedPreferences: SharedPreferences
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    private fun notifyChanges() {
        sharedPreferences.edit().putString("NOTES", serialize()).apply()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.itemView.tView.text = note.note
    }

    fun updateNotes(notes: MutableList<Note>) {
        this.notes = notes
        notifyChanges()
    }

    fun addNote(note: Note) {
        this.notes.add(note)
        notifyChanges()
    }

    private fun serialize(): String {
        var serializedString = ""
        notes.forEach {
            serializedString += it.note + "\n"
        }
        return serializedString
    }
}