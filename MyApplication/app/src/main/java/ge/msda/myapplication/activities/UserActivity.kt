package ge.msda.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import ge.msda.myapplication.R
import ge.msda.myapplication.api.RetrofitClient
import ge.msda.myapplication.api.dto.UserInfo
import kotlinx.android.synthetic.main.activity_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val userId = intent.extras?.getLong("USER_ID")

        if (userId != null) {

            RetrofitClient.reqResApi.getUserInfo(userId).enqueue(object : Callback<UserInfo> {

                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    if (response.isSuccessful && response.body() != null) {
                        updateUI(response.body()!!)
                    }
                }

            })

        }


    }

    private fun updateUI(userInfo: UserInfo) {
        val fullName = "${userInfo.data.firstName} ${userInfo.data.lastName}"
        fullNameTextView.text = fullName
        emailTextView.text = userInfo.data.email
        Picasso.get()
            .load(userInfo.data.avatar)
            .into(avatarImageView)
        compoanyTextView.text = userInfo.ad.company
        urlTextView.text = userInfo.ad.url
        textTextView.text = userInfo.ad.text
    }

}