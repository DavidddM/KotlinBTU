package com.example.midterm2.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.midterm2.App
import com.example.midterm2.R
import com.example.midterm2.models.ExpenseEntity
import kotlinx.android.synthetic.main.first_fragment.*
import kotlinx.android.synthetic.main.first_fragment.view.*
import java.lang.Exception

class FirstFragment : Fragment(R.layout.first_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.AddButton.setOnClickListener {
            var message = "Expense saved.."
            if (!TextUtils.isEmpty(editText.text.toString())) {
                try {
                    val expenseEntity = ExpenseEntity(
                        0,
                        editText.text.toString().toDouble(),
                        planets_spinner.selectedItem.toString()
                    )
                    App.instance.db.getExpenseDao().addExpense(expenseEntity)
                } catch (e: Exception) {
                    message = "Expense cannot be saved.. Error."
                } finally {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    editText.text.clear()
                    planets_spinner.setSelection(0)
                }
            }
        }
    }
}