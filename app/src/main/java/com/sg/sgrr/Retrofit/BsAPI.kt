package com.sg.sgrr.Retrofit

import com.sg.sgrr.nickname
import retrofit2.Call
import retrofit2.http.*

interface BsAPI {

    @Headers(
            "Accept: application/json",
            "x-api-key: ou9Gri9uej5wx94o3dOYa3rB1ZJ42o0SroCQx5rj")
    @GET("v1/data/{metaType}")
    fun getData(
            @Path ("metaType") metaType: String,
    )
    // GetData 추가적인 클래스 설정 필요함

    @Headers(
            "Accept: application/json",
            "x-api-key: ou9Gri9uej5wx94o3dOYa3rB1ZJ42o0SroCQx5rj")
    @GET("v1/rank/top/{seasonId}/{matchingTeamMode}")
    fun getRankTop(
            @Path ("seasonId") seasonId: String,
            @Path ("matchingTeamMode") matchingTeamMode: String
    ) // 추가적인 클래스 추가 필요

    @Headers(
            "Accept: application/json",
            "x-api-key: ou9Gri9uej5wx94o3dOYa3rB1ZJ42o0SroCQx5rj")
    @GET("v1/user/stats/{userNum}/{seasonId}/{matchingTeamMode}")
    fun getRankUser(
            @Path ("userNum") userNum: String,
            @Path ("seasonId") seasonId: String,
            @Path ("matchingTeamMode") matchingTeamMode: String
    ) // 추가적인 클래스 필요

    @Headers(
        "Accept: application/json"
    )
    @GET("v1/user/nickname")
    fun getUserNum(
        @Query("query") query: String
    ): Call<nickname>

    @Headers(
            "Accept: application/json"
    )
    @GET("v1/user/games/{userNum}")
    fun getUserGames(
        @Path ("userNum") userNum: String
    ): Call<games>

    @Headers(
            "Accept: application/json")
    @GET("v1/user/stats/{userNum}/{seasonId}")
    fun getUserStats(
            @Path ("userNum") userNum: String,
            @Path ("seasonId") seasonId: String
    ): Call<stats>

}