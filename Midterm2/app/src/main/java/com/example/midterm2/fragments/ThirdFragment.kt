package com.example.midterm2.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.midterm2.App
import com.example.midterm2.R
import com.example.midterm2.adapters.ExpenseAdapter
import com.example.midterm2.models.Expense
import com.example.midterm2.models.ExpenseEntity
import kotlinx.android.synthetic.main.third_fragment.*

class ThirdFragment: Fragment(R.layout.third_fragment) {
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setExpenses(App.instance.db.getExpenseDao().getAllExpenses())
    }

    private fun initView() {
        expenseAdapter = ExpenseAdapter(ArrayList())
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.adapter = expenseAdapter
    }

    fun setExpenses(expenses: List<ExpenseEntity>) {
        val expensesMutable: MutableList<Expense> = mutableListOf()
        expenses.forEach {
            expensesMutable.add(Expense(it.id, it.amount, it.expenseType))
        }
        expenseAdapter.updateExpenses(
            expensesMutable
        )
    }
}