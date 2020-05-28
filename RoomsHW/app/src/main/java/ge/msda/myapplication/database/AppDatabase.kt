package ge.msda.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ge.msda.myapplication.models.NoteEntity
import ge.msda.myapplication.repository.NoteDao

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}