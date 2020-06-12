package com.example.midterm2.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.midterm2.models.ExpenseEntity
import com.example.midterm2.models.ExpenseReport

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM EXPENSES")
    fun getAllExpenses(): List<ExpenseEntity>

    @Query("SELECT ExpenseType, sum(amount) as amount FROM EXPENSES GROUP BY ExpenseType")
    fun getExpenseReport(): List<ExpenseReport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addExpense(vararg notes: ExpenseEntity): List<Long>

    @Query("DELETE FROM EXPENSES")
    fun deleteAll()
}