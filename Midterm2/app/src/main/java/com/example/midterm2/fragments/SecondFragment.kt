package com.example.midterm2.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.midterm2.App
import com.example.midterm2.R
import kotlinx.android.synthetic.main.second_fragment.*

class SecondFragment: Fragment(R.layout.second_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reportList = App.instance.db.getExpenseDao().getExpenseReport()
        val sb = StringBuilder()
        reportList.forEach {
            sb.append("${it.expenseType} - $${it.amount}\n")
        }
        reportTextView.text = sb.toString()
    }
}