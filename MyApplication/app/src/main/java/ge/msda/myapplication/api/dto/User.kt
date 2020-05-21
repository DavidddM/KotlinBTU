package ge.msda.myapplication.api.dto

import com.google.gson.annotations.SerializedName

data class User(

    val id: Long,

    val email: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    val avatar: String

)

data class ReqResData<T>(

    val page: Int,

    val data: T

)

data class Ad(

    val company: String,

    val url: String,

    val text: String

)

data class UserInfo(

    val data: User,

    val ad: Ad

)