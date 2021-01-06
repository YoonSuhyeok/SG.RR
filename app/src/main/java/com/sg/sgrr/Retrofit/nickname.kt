package com.sg.sgrr

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultBsAPI(
    @SerializedName("code") @Expose val code: Int,
    @SerializedName("message") @Expose val message: String,
    @SerializedName("user") @Expose val user: user)

data class user(
    @SerializedName("userNum") @Expose val userNum: Int,
    @SerializedName("nickname") @Expose val nickname: String)