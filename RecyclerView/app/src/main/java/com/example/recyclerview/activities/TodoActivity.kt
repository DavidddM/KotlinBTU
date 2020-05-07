package com.example.recyclerview.activities
import android.os.Bundle
import android.widget.Toast
import com.example.recyclerview.R

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerview.adapters.TodoAdapter
import com.example.recyclerview.api.RetrofitClientInstance
import com.example.recyclerview.api.TodoService
import com.example.recyclerview.api.dto.Todo
import kotlinx.android.synthetic.main.activity_todo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        this.initView()
        val userId = intent.extras?.getLong("USER_ID")
        if (userId != null) {
            this.getUserTodos(userId)
        }
    }

    private fun initView() {
        todoAdapter = TodoAdapter(ArrayList<Todo>())
        recyclerViewTodo.layoutManager = GridLayoutManager(this, 1)
        recyclerViewTodo.adapter = todoAdapter
    }

    private fun getUserTodos(uid: Long) {
        val service = RetrofitClientInstance.retrofitInstance?.create(TodoService::class.java)
        val call = service?.getUserTodos(uid)
        call?.enqueue(object : Callback<List<Todo>> {
            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error Reading JSON", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<Todo>>?, response: Response<List<Todo>>?) {
                val body = response?.body()
                todoAdapter.updateTodos(body!!)
            }
        })
    }
}