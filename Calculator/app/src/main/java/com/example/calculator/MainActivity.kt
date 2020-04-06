package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var operation: String = ""
    private var operand: Double = 0.0

    private var startOver: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        equals.setOnClickListener {
            this.equals()
        }

        clear.setOnClickListener {
            this.reset()
        }
    }

    private fun evaluate(first: Double, second: Double, operation: String): Double {
        var returnVal = 0.0
        when (operation) {
            "+" -> returnVal = first + second
            "-" -> returnVal = first - second
            "*" -> returnVal = first * second
            "/" -> returnVal = if (second == 0.0) 0.0 else first / second
        }
        return "%.7f".format(returnVal).toDouble()
    }

    private fun equals() {
        if (!TextUtils.isEmpty(resultText.text)) {
            val currentValue = resultText.text.toString().toDouble()
            this.operand = this.evaluate(this.operand, currentValue, this.operation)
            this.renderResult()
            this.operation = ""
            this.startOver = true
        }
    }

    private fun renderOperand(): String {
        var value = this.operand.toString()
        try {
            val parts = value.split(".")
            if (parts[1] == "0") {
                value = parts[0]
            }
        } finally {
            return value
        }
    }

    private fun renderResult() {
        this.cacheText.text = ""
        this.resultText.text = this.renderOperand()
    }

    private fun renderCache() {
        this.cacheText.text = this.renderOperand() + " " + this.operation
        this.resultText.text = ""
    }

    fun reset() {
        this.operation = ""
        this.operand = 0.0
        this.cacheText.text = ""
        this.resultText.text = "0"
    }

    fun operationClick(view: View) {
        if (view is TextView) {
            if (!TextUtils.isEmpty(resultText.text)) {
                val currentValue = resultText.text.toString().toDouble()
                if (TextUtils.isEmpty(this.operation)) {
                    this.operand = currentValue
                } else {
                    this.equals()
                }

            }
            this.operation = view.text.toString()
            this.renderCache()
        }
    }

    fun numberClick(view: View) {
        if (view is TextView) {
            if (resultText.text.toString() == "0") {
                this.startOver = true
            }
            if (!TextUtils.isEmpty(resultText.text) || view.text.toString() != "0")
                if (this.startOver) {
                    resultText.text = view.text.toString()
                    this.startOver = false
                } else {
                    resultText.append(view.text.toString())
                }
        }
    }

    fun decPoint(view: View) {
        if (view is TextView) {
            if (!TextUtils.isEmpty(resultText.text) && resultText.text.last() != '.')
                resultText.append(".")
        }
    }

    fun del(view: View) {
        if (view is TextView) {
            if (TextUtils.isEmpty(resultText.text) || this.startOver) {
                this.reset()
            } else if (!TextUtils.isEmpty(resultText.text)) {
                if (resultText.text.length == 1) {
                    resultText.text = "0"
                } else {
                    resultText.text = resultText.text.substring(0, resultText.text.length - 1)
                }
            }
        }
    }
}
