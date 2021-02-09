package com.sg.sgrr.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.RecyclerView
import com.sg.sgrr.R
import com.sg.sgrr.Retrofit.userGames
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class resultRecordAdapter(private val recordList: List<userGames>, private val charImageArray: List<Int>, private val charName: List<String>): RecyclerView.Adapter<resultRecordAdapter.Holder>() {

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
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.rankNumber.text = "#${recordList[position].gameRank.toString()}"
        holder.mode.text = mode(recordList[position].seasonId, recordList[position].matchingTeamMode)
        holder.date.text = date(recordList[position].startDtm)
        val charNumber = recordList[position].characterNum
        holder.charImage.setImageResource( charImageArray[ charNumber] )
        holder.charName.text = charName[ charNumber ]
        holder.level.text = recordList[position].characterLevel.toString()
        holder.kda.text = "${recordList[position].playerKill} / ${recordList[position].monsterKill} / ${recordList[position].playerAssistant}"
    }

    private fun date(startDtm: String): CharSequence? {
        val startDay = startDtm.substring(0..9)
        if(startDay == today){
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
        var result = otherDay.time - currentDay.time
        result = abs(result / (24*60*60*1000))
        return "${ result }일 전"
    }

    private fun mode(seasonId: Int, matchingTeamMode: Int): CharSequence? {
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_result_fragment, parent, false)
        return Holder(view)
    }

}