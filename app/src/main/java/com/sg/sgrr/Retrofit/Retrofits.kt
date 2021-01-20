package com.sg.sgrr.Retrofit

import com.sg.sgrr.Retrofit.Retrofits.RetrofitClient.client
import com.sg.sgrr.nickname
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofits {

    object RetrofitClient {
        //val Base_URL_BSURL = "https://open-api.bser.io"
        val Base_URL_BSURL = "https://testbsserver.herokuapp.com"
        val retrofit = Retrofit.Builder().baseUrl(Base_URL_BSURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val client = retrofit.create(BsAPI::class.java)
    }
/*
    fun fetchUserNum(nicknames:String){
        val client = RetrofitClient.retrofit.create(BsAPI::class.java)
        client.getUserNum(nicknames).enqueue(object: Callback<nickname>{
            override fun onResponse(call: Call<nickname>, response: Response<nickname>) {
                if(response.isSuccessful){
                    fetchUserStats(response.body()?.user?.userNum.toString())
                }
            }

            override fun onFailure(call: Call<nickname>, t: Throwable) {
                //TODO("Not yet implemented")
            }

        })
    }

    fun fetchUserStats(userNum: String){

        client.getUserStats( "482298", "1").enqueue(object: Callback<stats>{
            override fun onResponse(call: Call<stats>, response: Response<stats>) {
                println(response.body()?.userStats?.get(0)?.nickname)
            }

            override fun onFailure(call: Call<stats>, t: Throwable) {
                //TODO("Not yet implemented")
            }

        })
    }

 */
}