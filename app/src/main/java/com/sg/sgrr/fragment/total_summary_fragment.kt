package com.sg.sgrr.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sg.sgrr.R

class total_summary_fragment : Fragment() {

    lateinit var c1:ImageView
    lateinit var c2:ImageView
    lateinit var c3:ImageView
    lateinit var cname1:TextView
    lateinit var cname2:TextView
    lateinit var cname3:TextView
    lateinit var avRank1:TextView
    lateinit var avRank2:TextView
    lateinit var avRank3:TextView
    lateinit var games1:TextView
    lateinit var games2:TextView
    lateinit var games3:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val newView = inflater.inflate(R.layout.fragment_total_summary, container, false)
        c1 = newView.findViewById(R.id.total_img_c1)
        c2 = newView.findViewById(R.id.total_img_c2)
        c3 = newView.findViewById(R.id.total_img_c3)
        cname1 = newView.findViewById(R.id.total_txt_cname1)
        cname2 = newView.findViewById(R.id.total_txt_cname2)
        cname3 = newView.findViewById(R.id.total_txt_cname3)
        avRank1 = newView.findViewById(R.id.total_txt_avRank1)
        avRank2 = newView.findViewById(R.id.total_txt_avRank2)
        avRank3 = newView.findViewById(R.id.total_txt_avRank3)
        games1 = newView.findViewById(R.id.total_txt_games1)
        games2 = newView.findViewById(R.id.total_txt_games2)
        games3 = newView.findViewById(R.id.total_txt_games3)
        return newView
    }

    // c1 바꾸기
    fun changeC1(charImg:Int, cname:String, avRank:String, games:String){
        c1.setImageResource(charImg)
        cname1.setText(cname)
        avRank1.setText(avRank)
        games1.setText(games)
    }

    // c2 바꾸기
    fun changeC2(charImg:Int, cname:String, avRank:String, games:String){
        c2.setImageResource(charImg)
        cname2.setText(cname)
        avRank2.setText(avRank)
        games2.setText(games)
    }

    // c3 바꾸기
    fun changeC3(charImg:Int, cname:String, avRank:String, games:String){
        c3.setImageResource(charImg)
        cname3.setText(cname)
        avRank3.setText(avRank)
        games3.setText(games)
    }
}