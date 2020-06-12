package com.example.midterm2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.midterm2.models.ExpenseEntity
import com.example.midterm2.repository.ExpenseDao

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getExpenseDao(): ExpenseDao
}