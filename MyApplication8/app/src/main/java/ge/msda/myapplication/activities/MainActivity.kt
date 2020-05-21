package ge.msda.myapplication.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import ge.msda.myapplication.R
import ge.msda.myapplication.adapters.NoteAdapter
import ge.msda.myapplication.models.Note
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var noteAdapter: NoteAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("MyApplication", Context.MODE_PRIVATE)
        val notesStr = sharedPreferences.getString("NOTES", "")

        addBtn.setOnClickListener {
            val input = inputText.text.toString()
            if (!TextUtils.isEmpty(input)) {
                addNote(input)
                inputText.setText("")
            }
        }
        clrBtn.setOnClickListener {
            inputText.setText("")
            noteAdapter.updateNotes(ArrayList())
        }
        initView(sharedPreferences)
        setNotes(notesStr)
    }

    private fun initView(sharedPreferences: SharedPreferences) {
        noteAdapter = NoteAdapter(ArrayList(), sharedPreferences)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = noteAdapter
    }

    private fun setNotes(noteStr: String?) {
        if (noteStr != null) {
            val notes: MutableList<Note> = mutableListOf()
            noteStr.lines().dropLast(1).forEach {
                notes.add(Note(it))
            }
            noteAdapter.updateNotes(
                notes
            )
        }
    }

    private fun addNote(text: String) {
        var message = "Note added.."
        try {
            noteAdapter.addNote(Note(text))
        } catch (e: Exception) {
            message = "Note cannot be added.. Error."
        } finally {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}