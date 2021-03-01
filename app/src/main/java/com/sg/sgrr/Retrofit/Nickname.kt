package com.sg.sgrr.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Nickname(
    @SerializedName("code") @Expose val code: Int,
    @SerializedName("message") @Expose val message: String,
    @SerializedName("user") @Expose val User: User)

data class User(
    @SerializedName("userNum") @Expose val userNum: Int,
    @SerializedName("nickname") @Expose val nickname: String)