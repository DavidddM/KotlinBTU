package ge.msda.myapplication.repository

import androidx.room.*
import ge.msda.myapplication.models.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM NOTES")
    fun getAllNotes(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(vararg notes: NoteEntity): List<Long>

    @Query("DELETE FROM NOTES")
    fun deleteAll()
}