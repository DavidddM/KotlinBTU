package com.example.recyclerview.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.api.dto.Todo
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter(private var todos: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private lateinit var todo: Todo

        fun bind(todo: Todo) {
            this.todo = todo
            itemView.taskNameTextView.text = todo.title
            if (todo.completed) {
                itemView.taskNameTextView.setTextColor(Color.GREEN)
            }
            else {
                itemView.taskNameTextView.setTextColor(Color.RED)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)

        return TodoViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = todos.size;

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    fun updateTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }
}