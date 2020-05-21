package ge.msda.myapplication.api

import ge.msda.myapplication.api.dto.ReqResData
import ge.msda.myapplication.api.dto.User
import ge.msda.myapplication.api.dto.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresService {

    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<ReqResData<List<User>>>

    @GET("users/{userId}")
    fun getUserInfo(@Path("userId") userId: Long): Call<UserInfo>

}