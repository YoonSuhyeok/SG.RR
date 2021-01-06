package com.sg.sgrr.Retrofit

import com.sg.sgrr.ResultBsAPI
import retrofit2.Call
import retrofit2.http.*

interface BsAPI {
    @Headers(
        "Accept: application/json",
        "x-api-key: ou9Gri9uej5wx94o3dOYa3rB1ZJ42o0SroCQx5rj"
    )
    @GET("v1/user/nickname")
    fun getUserNum(
        @Query("query") query: String
    ): Call<ResultBsAPI>

    @Headers(
            "Accept: application/json",
            "x-api-key: ou9Gri9uej5wx94o3dOYa3rB1ZJ42o0SroCQx5rj",
    )
    @GET("v1/user/games/{userNum}")
    fun getUserGames(
        @Path ("userNum") userNum: String,
        @Query ("next") next: String
    )

    @Headers(
            "Accept: application/json",
            "x-api-key: ou9Gri9uej5wx94o3dOYa3rB1ZJ42o0SroCQx5rj",
    )
    @GET("v1/user/stats/{userNum}/{seasonId}")
    fun getUserStats(
            @Path ("seasonId") seasonId: String,
            @Path ("userNum") userNum: String
    )
}