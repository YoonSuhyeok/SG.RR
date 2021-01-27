package com.sg.sgrr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sg.sgrr.Adapter.MainRecordAapter
import com.sg.sgrr.Retrofit.BsAPI
import com.sg.sgrr.Retrofit.Retrofits
import com.sg.sgrr.Retrofit.stats
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Retrofits().fetchUserNum("마우스조경")

        val Base_URL_BSURL = "https://testbsserver.herokuapp.com"
        val retrofit = Retrofit.Builder().baseUrl(Base_URL_BSURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val client = retrofit.create(BsAPI::class.java)
        //coroutines

        val editText = findViewById<EditText>(R.id.editText)

        val mainRecordAapter = MainRecordAapter(ArrayList<MainRecord>())
        val recyclerView = findViewById<RecyclerView>(R.id.mainRecycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
        )
        recyclerView.adapter = mainRecordAapter

        findViewById<Button>(R.id.search_btn_main).setOnClickListener{
            client.getUserNum(editText.text.toString()).enqueue(object : Callback<nickname> {
                override fun onResponse(call: Call<nickname>, response: Response<nickname>) {
                    if(response.body()?.code == 200) {
                        val intent = Intent(this@MainActivity, RecordActivitys::class.java)
                        intent.putExtra("UserNumber", response.body()?.user?.userNum)
                        intent.putExtra("UserNickname", editText.text.toString())
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "존재하지 않는 연구체입니다", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<nickname>, t: Throwable) {

                }
            })
        }
        //startActivity(Intent(this, RecordActivity::class.java))

    }
}