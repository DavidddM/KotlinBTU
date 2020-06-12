package com.example.midterm2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm2.R
import com.example.midterm2.models.Expense
import kotlinx.android.synthetic.main.expense_item.view.*

class ExpenseAdapter(
    private var expenses: MutableList<Expense>
) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {
    class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun getItemCount(): Int = expenses.size

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.itemView.expType.text = expense.expenseType
        holder.itemView.amount.text = "$" + expense.amount.toString()
    }

    fun updateExpenses(expenses: MutableList<Expense>) {
        this.expenses = expenses
        notifyDataSetChanged()
    }
}