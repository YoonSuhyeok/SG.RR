package com.sg.sgrr.Retrofit

import com.sg.sgrr.ResultBsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofits {

    object RetrofitClient {
        val Base_URL_BSURL = "https://open-api.bser.io"
        val retrofit = Retrofit.Builder().baseUrl(Base_URL_BSURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun fetchUserNum(){
        val client = RetrofitClient.retrofit.create(BsAPI::class.java)

        client.getUserNum("마우스조경").enqueue(object: Callback<ResultBsAPI> {
            override fun onResponse(call: Call<ResultBsAPI>, response: Response<ResultBsAPI>) {
                // 자동으로 동기화가 안 됨.
                println(response.raw())
                val a: ResultBsAPI? = response.body()
                println(a?.user?.userNum)
            }

            override fun onFailure(call: Call<ResultBsAPI>, t: Throwable) {

            }

        })
    }
}