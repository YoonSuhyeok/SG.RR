package com.sg.sgrr.Adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sg.sgrr.MainRecord
import com.sg.sgrr.R

class MainRecordAapter(private val MainRecordList: ArrayList<MainRecord>): RecyclerView.Adapter<MainRecordAapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_record_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: MainRecordAapter.Holder, position: Int) {
        holder.UserName.text = MainRecordList[position].UserName
        holder.rank.text = MainRecordList[position].mmr
        holder.ranknumber.text = MainRecordList[position].rank
        holder.ranknumber2.text = "위 (상위 ${MainRecordList[position].rankPercent}%)"
//        holder.most1Text.text = "평균 ${MainRecordList[position].most1Text}위"
        //holder.most2Text.text = "평균 ${MainRecordList[position].most2Text}위"
        //holder.most3Text.text = "평균 ${MainRecordList[position].most3Text}위"

        holder.MainCharImage.setImageResource(MainRecordList[position].mainMost)
//        holder.most1.setImageDrawable()
//        holder.most2.setImageDrawable()
//        holder.most3.setImageDrawable()
    }

    override fun getItemCount(): Int {
        return MainRecordList.size
    }

    fun add(mainRecord: MainRecord){
        MainRecordList.add(mainRecord)
        notifyItemChanged(MainRecordList.size)
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var MainCharImage: ImageView = itemView.findViewById(R.id.UserName)
        var UserName     : TextView  = itemView.findViewById(R.id.UserName)
        var rank         : TextView  = itemView.findViewById(R.id.rank)
        var ranknumber   : TextView  = itemView.findViewById(R.id.ranknumber)
        var ranknumber2  : TextView  = itemView.findViewById(R.id.ranknumber2)
        var most1        : ImageView = itemView.findViewById(R.id.most1)
        var most1Text    : TextView  = itemView.findViewById(R.id.most1Text)
        var most2        : ImageView = itemView.findViewById(R.id.most2)
        var most2Text    : TextView  = itemView.findViewById(R.id.most2Text)
        var most3        : ImageView = itemView.findViewById(R.id.most3)
        var most3Text    : TextView  = itemView.findViewById(R.id.most3Text)
    }


}