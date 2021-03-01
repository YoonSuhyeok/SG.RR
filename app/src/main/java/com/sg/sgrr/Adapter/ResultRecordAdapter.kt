package com.sg.sgrr.Adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.sg.sgrr.Etc.Items.Companion.charImageArray
import com.sg.sgrr.Etc.Items.Companion.charNameArray
import com.sg.sgrr.Etc.Items.Companion.itemGrade
import com.sg.sgrr.Etc.Items.Companion.itemImageMap
import com.sg.sgrr.R
import com.sg.sgrr.Retrofit.RecordElement
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class ResultRecordAdapter(private val recordList: MutableList<RecordElement>): RecyclerView.Adapter<ResultRecordAdapter.Holder>(){

    @SuppressLint("SimpleDateFormat")
    private val today = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Date())
    private val dayFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private val currentDay = dayFormat.parse(today.substring(0..9))

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rankNumber: TextView = itemView.findViewById(R.id.record_txt_ranknum)
        val mode: TextView = itemView.findViewById(R.id.record_txt_mode)
        val date: TextView = itemView.findViewById(R.id.record_txt_date)
        val charImage: ImageView = itemView.findViewById(R.id.charImage)
        val charName: TextView = itemView.findViewById(R.id.charName)
        val level:TextView = itemView.findViewById(R.id.record_txt_level)
        val kda:TextView = itemView.findViewById(R.id.record_txt_kda)
        val duration:TextView = itemView.findViewById(R.id.duration)
        val image1:ImageView = itemView.findViewById(R.id.item1)
        val image2:ImageView = itemView.findViewById(R.id.item2)
        val image3:ImageView = itemView.findViewById(R.id.item3)
        val image4:ImageView = itemView.findViewById(R.id.item4)
        val image5:ImageView = itemView.findViewById(R.id.squad_underbar)
        val image6:ImageView = itemView.findViewById(R.id.item6)
        val layout:ConstraintLayout = itemView.findViewById(R.id.layout_recordLayout)
        val leftBar:View = itemView.findViewById(R.id.sideBar)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.rankNumber.text = "#" + recordList[position].userGame.gameRank
        holder.mode.text = mode(recordList[position].userGame.seasonId, recordList[position].userGame.matchingTeamMode)
        holder.date.text = date(recordList[position].userGame.startDtm)
        val charNumber = recordList[position].userGame.characterNum
        holder.charImage.setImageResource( charImageArray[ charNumber - 1] )
        holder.charName.text = charNameArray[ charNumber - 1]
        holder.level.text = recordList[position].userGame.characterLevel.toString()
        holder.kda.text = "${recordList[position].userGame.playerKill} / ${recordList[position].userGame.playerAssistant} / ${recordList[position].userGame.monsterKill}"

        if ( (recordList[position].userGame.duration%60)/10 != 0 )
            holder.duration.text = "${recordList[position].userGame.duration/60}:" +recordList[position].userGame.duration%60
        else
            holder.duration.text = "${recordList[position].userGame.duration/60}:0" +recordList[position].userGame.duration%60

        when(recordList[position].userGame.gameRank){
            1,2,3,4,5 -> {
                holder.layout.setBackgroundColor(Color.parseColor("#DBEEFF"))
                holder.leftBar.setBackgroundColor(Color.parseColor("#369FFF"))
                holder.rankNumber.setTextColor(Color.parseColor("#369FFF"))
            }
            11,12,13,14,15,16 -> {
                holder.layout.setBackgroundColor(Color.parseColor("#FFE9E9"))
                holder.leftBar.setBackgroundColor(Color.parseColor("#C13737"))
                holder.rankNumber.setTextColor(Color.parseColor("#C13737"))
            }
        }
        itemImageMap[ recordList[position].userGame.equipment["0"] ]?.let {
            holder.image1.apply {
                setImageResource(it)
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].userGame.equipment["0"] ]) )
            }
        }
        itemImageMap[ recordList[position].userGame.equipment["1"] ]?.let {
            holder.image2.apply {
                setImageResource(it)
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].userGame.equipment["1"] ]) )
            }
        }
        itemImageMap[ recordList[position].userGame.equipment["2"] ]?.let {
            holder.image3.apply {
                setImageResource(it)
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].userGame.equipment["2"] ]) )
            }
        }
        itemImageMap[ recordList[position].userGame.equipment["3"] ]?.let {
            holder.image4.apply {
                setImageResource(it)
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].userGame.equipment["3"] ]) )
            }
        }
        itemImageMap[ recordList[position].userGame.equipment["4"] ]?.let {
            holder.image5.apply {
                setImageResource(it)
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].userGame.equipment["4"] ]) )
            }
        }
        itemImageMap[ recordList[position].userGame.equipment["5"] ]?.let {
            holder.image6.apply {
                setImageResource(it)
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].userGame.equipment["5"] ]) )
            }
        }
    }

    private fun date(startDtm: String): CharSequence {
        val startDay = startDtm.substring(0..9)
        if(startDay == today.substring(0..9)){
            val currentHour = today.substring(11..12).toInt(); val startHour = startDtm.substring(11..12).toInt()
            if(currentHour == startHour){
                val currentMinute = today.substring(14..15).toInt(); val startMinute = startDtm.substring(14..15).toInt()
                if(currentMinute == startMinute){
                    return "${today.substring(17..18).toInt() - startDtm.substring(17..18).toInt()}초 전"
                }
                return "${currentMinute - startMinute}분 전"
            }
            return "${currentHour - startHour}시간 전"
        }

        val otherDay = dayFormat.parse(startDay)
        var result = otherDay!!.time - currentDay!!.time
        result = abs(result / (24*60*60*1000))
        return "${ result }일 전"
    }

    private fun mode(seasonId: Int, matchingTeamMode: Int): CharSequence {
        if(seasonId == 0){
            return when(matchingTeamMode){
                1 -> "일반 솔로"
                2 -> "일반 듀오"
                else -> "일반 스쿼드"
            }
        }
        return when(matchingTeamMode){
            1 -> "솔로 랭크"
            2 -> "듀오 랭크"
            else -> "스쿼드 랭크"
        }
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_result_list, parent, false)
        return Holder(view)
    }

    fun color(s: String?): Int {
        return when(s){
            "Common" -> Color.parseColor("#B1B3B3")
            "Uncommon" -> Color.parseColor("#6DE7AB")
            "Rare" -> Color.parseColor("#7583FF")
            "Epic" -> Color.parseColor("#BB73FF")
            else -> Color.parseColor("#FEFF91")
        }
    }
}