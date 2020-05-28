package ge.msda.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import ge.msda.myapplication.App
import ge.msda.myapplication.R
import ge.msda.myapplication.adapters.NoteAdapter
import ge.msda.myapplication.models.Note
import ge.msda.myapplication.models.NoteEntity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setNotes(App.instance.db.getNoteDao().getAllNotes())

        initToolbar("Rooms")
    }

    private fun initToolbar(title: String) {
        toolbar.title = title
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_delete -> {
                inputText.setText("")
                noteAdapter.updateNotes(ArrayList())
                App.instance.db.getNoteDao().deleteAll()
                return true
            }
            R.id.action_add -> {
                val input = inputText.text.toString()
                if (!TextUtils.isEmpty(input)) {
                    addNote(input)
                    inputText.setText("")
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        noteAdapter = NoteAdapter(ArrayList())
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = noteAdapter
    }

    private fun setNotes(notes: List<NoteEntity>) {
        val notesMutable: MutableList<Note> = mutableListOf()
        notes.forEach {
            notesMutable.add(Note(it.id, it.note))
        }
        noteAdapter.updateNotes(
            notesMutable
        )
    }

    private fun addNote(text: String) {
        var message = "Note added.."
        try {
            val noteEntity = NoteEntity(0, text)
            noteAdapter.addNote(
                Note(
                    App.instance.db.getNoteDao().addNote(noteEntity).first(),
                    noteEntity.note
                )
            )
        } catch (e: Exception) {
            message = "Note cannot be added.. Error."
        } finally {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}