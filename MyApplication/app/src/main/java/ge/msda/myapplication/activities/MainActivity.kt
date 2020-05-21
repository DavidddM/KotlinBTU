package ge.msda.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ge.msda.myapplication.R
import ge.msda.myapplication.adapters.UserAdapter
import ge.msda.myapplication.api.RetrofitClient
import ge.msda.myapplication.api.dto.ReqResData
import ge.msda.myapplication.api.dto.User
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initRecycler()

        this.getAllUsers()

    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this)
        userList.layoutManager = layoutManager
        userAdapter = UserAdapter(ArrayList())
        userList.adapter = userAdapter
    }

    private fun getAllUsers() {

        RetrofitClient.reqResApi.getUsers(2).enqueue(object : Callback<ReqResData<List<User>>> {

            override fun onFailure(call: Call<ReqResData<List<User>>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ReqResData<List<User>>>,
                response: Response<ReqResData<List<User>>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    userAdapter.updateUsers(response.body()!!.data)
                }
            }

        })

    }

}
