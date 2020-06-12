package com.example.midterm2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EXPENSES")
data class ExpenseEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ExpenseID")
    val id: Long,
    @ColumnInfo(name = "Amount")
    val amount: Double,
    @ColumnInfo(name = "ExpenseType")
    val expenseType: String
)