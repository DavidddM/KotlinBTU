package com.example.midterm2.models

import androidx.room.ColumnInfo

data class ExpenseReport (
    @ColumnInfo(name = "ExpenseType")
    val expenseType: String,
    @ColumnInfo(name = "amount")
    val amount: Double
)