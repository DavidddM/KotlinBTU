package ge.msda.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NOTES")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NoteID")
    val id: Long,
    @ColumnInfo(name = "NoteText")
    val note: String
)