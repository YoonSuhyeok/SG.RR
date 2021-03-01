package com.sg.sgrr.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sg.sgrr.Adapter.MainRecordAdapter
import com.sg.sgrr.Adapter.VerticalSpaceItemDecoration
import com.sg.sgrr.DBHelper.DBHelper
import com.sg.sgrr.R
import com.sg.sgrr.Retrofit.Nickname
import com.sg.sgrr.Retrofit.Retrofits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val editText:EditText by lazy { findViewById(R.id.editText) }
    private val recyclerview: RecyclerView by lazy { findViewById(R.id.mainRecycler) }
    private var blackDB: DBHelper? = null
    private var mainRecordAdapter = MainRecordAdapter(ArrayList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blackDB = DBHelper(this)

        mainRecordAdapter = MainRecordAdapter(blackDB!!.getAllRecord())
        val spaceDecoration = VerticalSpaceItemDecoration(10)
        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(spaceDecoration)
            adapter = mainRecordAdapter
        }

        val searchButton:Button = findViewById(R.id.search_btn_main)
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.bounce)

        searchButton.setOnClickListener{
            if(editText.text.toString() == "") {
                Toast.makeText(this, "입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                searchButton.startAnimation(animation)
                requestNumber()
            }

        }

    }

    private fun requestNumber(){

        val userNumber = blackDB!!.getUserNumber(editText.text.toString())

        if (userNumber != 0)
        {
            val intent = Intent(this@MainActivity, RecordActivity::class.java)
            intent.putExtra("UserNumber", userNumber)
            intent.putExtra("UserNickname", editText.text.toString())
            startActivity(intent)
        }
        else
        {
            val load = findViewById<ConstraintLayout>(R.id.loading)
            load.visibility = View.VISIBLE
            Retrofits.client.getUserNum(editText.text.toString()).enqueue(object : Callback<Nickname> {
                override fun onResponse(call: Call<Nickname>, response: Response<Nickname>) {
                    if(response.body()?.code == 200) {
                        val intent = Intent(this@MainActivity, RecordActivity::class.java)
                        intent.putExtra("UserNumber", response.body()?.User?.userNum)
                        intent.putExtra("UserNickname", editText.text.toString())
                        startActivity(intent)
                        load.visibility = View.GONE
                    } else {
                        Toast.makeText(this@MainActivity, "존재하지 않는 연구체입니다", Toast.LENGTH_SHORT).show()
                        load.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<Nickname>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "서버 살아나는 중 다시 시도하세요", Toast.LENGTH_SHORT).show()
                    load.visibility = View.GONE
                }
            })
        }
    }
    override fun onResume() {
        super.onResume()
        if(editText.text != null) editText.text = null

        mainRecordAdapter = MainRecordAdapter(blackDB!!.getAllRecord())
        recyclerview.adapter = mainRecordAdapter
    }
}