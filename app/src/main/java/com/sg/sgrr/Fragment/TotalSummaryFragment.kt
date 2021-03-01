package com.sg.sgrr.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sg.sgrr.R

class TotalSummaryFragment : Fragment() {

    private lateinit var c1:ImageView
    private lateinit var c2:ImageView
    private lateinit var c3:ImageView
    private lateinit var cname1:TextView
    private lateinit var cname2:TextView
    private lateinit var cname3:TextView
    private lateinit var avRank1:TextView
    private lateinit var avRank2:TextView
    private lateinit var avRank3:TextView
    private lateinit var games1:TextView
    private lateinit var games2:TextView
    private lateinit var games3:TextView

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

        val args = arguments
        if(args != null){
            if(args.getInt("char1_img") == -1){
                c1.visibility = View.GONE
                cname1.visibility = View.GONE
                avRank1.visibility = View.GONE
                games1.visibility = View.GONE
            }else {
                c1.visibility = View.VISIBLE
                cname1.visibility = View.VISIBLE
                avRank1.visibility = View.VISIBLE
                games1.visibility = View.VISIBLE
                changeC1(args.getInt("char1_img", 0),
                        args.getString("char1_name"),
                        args.getString("char1_avgRank"),
                        args.getString("char1_games"))
            }
            if(args.getInt("char2_img") == -1) {
                c2.visibility = View.GONE
                cname2.visibility = View.GONE
                avRank2.visibility = View.GONE
                games2.visibility = View.GONE
            }else {
                c2.visibility = View.VISIBLE
                cname2.visibility = View.VISIBLE
                avRank2.visibility = View.VISIBLE
                games2.visibility = View.VISIBLE
                changeC2(args.getInt("char2_img", 0),
                        args.getString("char2_name"),
                        args.getString("char2_avgRank"),
                        args.getString("char2_games"))
            }
            if(args.getInt("char3_img") == -1){
                c3.visibility = View.GONE
                cname3.visibility = View.GONE
                avRank3.visibility = View.GONE
                games3.visibility = View.GONE
            }else {
                c3.visibility = View.VISIBLE
                cname3.visibility = View.VISIBLE
                avRank3.visibility = View.VISIBLE
                games3.visibility = View.VISIBLE
                changeC3(args.getInt("char3_img", 0), args.getString("char3_name"), args.getString("char3_avgRank"), args.getString("char3_games"))
            }
        }
//        else{
//
//        }

        return newView
    }

    // c1 바꾸기
    fun changeC1(charImg:Int, cname:String?, avRank:String?, games:String?){
        c1.setImageResource(charImg)
        cname1.text = cname
        avRank1.text = avRank
        games1.text = games
        c1.visibility = View.VISIBLE
        cname1.visibility = View.VISIBLE
        avRank1.visibility = View.VISIBLE
        games1.visibility = View.VISIBLE
    }

    // c2 바꾸기
    fun changeC2(charImg:Int, cname:String?, avRank:String?, games:String?){
        c2.setImageResource(charImg)
        cname2.text = cname
        avRank2.text = avRank
        games2.text = games
        c2.visibility = View.VISIBLE
        cname2.visibility = View.VISIBLE
        avRank2.visibility = View.VISIBLE
        games2.visibility = View.VISIBLE
    }

    // c3 바꾸기
    fun changeC3(charImg:Int, cname:String?, avRank:String?, games:String?){
        c3.setImageResource(charImg)
        cname3.text = cname
        avRank3.text = avRank
        games3.text = games
        c1.visibility = View.VISIBLE
        cname1.visibility = View.VISIBLE
        avRank1.visibility = View.VISIBLE
        games1.visibility = View.VISIBLE
    }

    fun inVisibleC1() {
        c1.visibility = View.GONE
        cname1.visibility = View.GONE
        avRank1.visibility = View.GONE
        games1.visibility = View.GONE
    }

    fun inVisibleC2() {
        c2.visibility = View.GONE
        cname2.visibility = View.GONE
        avRank2.visibility = View.GONE
        games2.visibility = View.GONE
    }

    fun inVisibleC3() {
        c3.visibility = View.GONE
        cname3.visibility = View.GONE
        avRank3.visibility = View.GONE
        games3.visibility = View.GONE
    }
}