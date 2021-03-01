package com.sg.sgrr.Retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class Retrofits {

    companion object RetrofitClient {
        //private const val Base_URL_BSURL = "https://open-api.bser.io"
        private const val Base_URL_BSURL = "https://testbsserver.herokuapp.com"
        //private const val Base_URL_BSURL = "http://54.180.153.7:3000"
        private val retrofit: Retrofit = Retrofit.Builder().baseUrl(Base_URL_BSURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val client: BsAPI = retrofit.create(BsAPI::class.java)
    }
}

interface BsAPI {

    @Headers(
            "Accept: application/json"
    )
    @GET("v1/user/nickname")
    fun getUserNum(
        @Query("query") query: String
    ): Call<Nickname>

    @Headers(
            "Accept: application/json"
    )
    @GET("v1/user/games/{userNum}")
    fun getUserGames(
        @Path ("userNum") userNum: String
    ): Call<Games>

    @Headers(
            "Accept: application/json"
    )
    @GET("v1/user/stats/{userNum}/{seasonId}")
    fun getUserStats(
            @Path ("userNum") userNum: String,
            @Path ("seasonId") seasonId: String
    ): Call<Stats>

}