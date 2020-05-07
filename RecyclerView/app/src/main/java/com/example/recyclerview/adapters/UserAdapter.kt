package com.example.recyclerview.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.activities.TodoActivity
import com.example.recyclerview.api.dto.User
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(private var users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }

        private lateinit var user: User

        fun bind(user: User) {

            this.user = user
            itemView.nameTextView.text = user.name
            itemView.usernameTextView.text = user.userName
            itemView.emailTextView.text = user.email
            itemView.websiteTextView.text = user.website
            itemView.phoneTextView.text = user.phoneNumber
        }

        override fun onClick(v: View?) {
            val context = itemView.context
            val intent = Intent(context, TodoActivity::class.java)
            intent.putExtra("USER_ID", user.id)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)

        return UserViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = users.size;

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

}