package com.example.recyclerview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerview.R
import com.example.recyclerview.api.dto.User
import com.example.recyclerview.adapters.UserAdapter
import com.example.recyclerview.api.RetrofitClientInstance
import com.example.recyclerview.api.UserService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var userAdapter: UserAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initView()
        this.getUsers()
    }

    private fun initView() {
        userAdapter = UserAdapter(ArrayList<User>())
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = userAdapter
    }

    private fun getUsers() {
        val service = RetrofitClientInstance.retrofitInstance?.create(UserService::class.java)
        val call = service?.getAllUsers()
        call?.enqueue(object : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error Reading JSON", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                val body = response?.body()
                userAdapter.updateUsers(body!!)
            }
        })
    }
}
