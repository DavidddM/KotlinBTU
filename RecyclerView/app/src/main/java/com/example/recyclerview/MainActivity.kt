package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ProductAdapter(getProducts())
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun getProducts(): List<Product> {
        return listOf(
            Product("Item 1", "USD 3500$", R.drawable.r1),
            Product("Item 2", "USD 1500$", R.drawable.r2),
            Product("Item 3", "USD 1500$", R.drawable.r3),
            Product("Item 4", "USD 1300$", R.drawable.r4),
            Product("Item 5", "USD 15200$", R.drawable.r5),
            Product("Item 6", "USD 1200$", R.drawable.r6),
            Product("Item 7", "USD 500$", R.drawable.r3),
            Product("Item 8", "USD 4500$", R.drawable.r1),
            Product("Item 9", "USD 3500$", R.drawable.r5),
            Product("Item 10", "USD 2500$", R.drawable.r4),
            Product("Item 11", "USD 1500$", R.drawable.r2),
            Product("Item 12", "USD 3500$", R.drawable.r1)
        )
    }
}
