package com.sg.sgrr.Adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sg.sgrr.R
import com.sg.sgrr.Retrofit.userGames
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class resultRecordAdapter(
        private val recordList: List<userGames>,
        private val charImageArray: List<Int>,
        private val itemImageMap: Map<Int, Int>,
        private val charName: List<String>): RecyclerView.Adapter<resultRecordAdapter.Holder>() {
    private val itemGrade = mapOf(
            101101 to "Common",
            101102 to "Common",
            101104 to "Common",
            101201 to "Uncommon",
            101301 to "Rare",
            101401 to "Epic",
            101402 to "Epic",
            101404 to "Epic",
            101405 to "Legend",
            102101 to "Common",
            102201 to "Uncommon",
            102301 to "Rare",
            102401 to "Rare",
            102402 to "Rare",
            102403 to "Rare",
            102404 to "Rare",
            102405 to "Rare",
            102406 to "Epic",
            102407 to "Epic",
            102408 to "Rare",
            102409 to "Legend",
            102410 to "Epic",
            102411 to "Epic",
            102501 to "Legend",
            103201 to "Uncommon",
            103301 to "Rare",
            103401 to "Epic",
            103402 to "Epic",
            103501 to "Epic",
            103502 to "Epic",
            104101 to "Common",
            104201 to "Uncommon",
            104301 to "Rare",
            104302 to "Rare",
            104401 to "Epic",
            104402 to "Epic",
            104403 to "Epic",
            104404 to "Epic",
            104405 to "Epic",
            105102 to "Common",
            105103 to "Common",
            105201 to "Uncommon",
            105202 to "Uncommon",
            105301 to "Rare",
            105302 to "Rare",
            105401 to "Rare",
            105402 to "Epic",
            105403 to "Epic",
            105404 to "Epic",
            105405 to "Epic",
            105406 to "Epic",
            107101 to "Common",
            107201 to "Uncommon",
            107301 to "Rare",
            107302 to "Rare",
            107303 to "Rare",
            107401 to "Rare",
            107402 to "Epic",
            107403 to "Epic",
            107404 to "Epic",
            107405 to "Epic",
            107406 to "Legend",
            107407 to "Epic",
            107408 to "Epic",
            107501 to "Legend",
            108101 to "Common",
            108102 to "Common",
            108202 to "Uncommon",
            108301 to "Rare",
            108401 to "Rare",
            108402 to "Rare",
            108403 to "Epic",
            108404 to "Epic",
            108501 to "Epic",
            108502 to "Legend",
            109101 to "Common",
            109201 to "Uncommon",
            109202 to "Uncommon",
            109301 to "Rare",
            109401 to "Rare",
            109402 to "Rare",
            109403 to "Epic",
            109404 to "Epic",
            109501 to "Legend",
            110101 to "Common",
            110102 to "Common",
            110201 to "Uncommon",
            110202 to "Uncommon",
            110301 to "Rare",
            110302 to "Rare",
            110401 to "Rare",
            110402 to "Rare",
            110403 to "Rare",
            110404 to "Rare",
            110405 to "Epic",
            110406 to "Epic",
            110407 to "Epic",
            110408 to "Epic",
            110409 to "Epic",
            110410 to "Epic",
            110411 to "Epic",
            110412 to "Epic",
            108103 to "Common",
            111201 to "Uncommon",
            111301 to "Rare",
            111401 to "Rare",
            111402 to "Epic",
            111403 to "Epic",
            111404 to "Epic",
            112103 to "Common",
            112105 to "Common",
            112202 to "Uncommon",
            112203 to "Uncommon",
            112204 to "Rare",
            112205 to "Uncommon",
            112301 to "Rare",
            112302 to "Epic",
            112303 to "Rare",
            112304 to "Rare",
            112305 to "Epic",
            112401 to "Epic",
            112402 to "Epic",
            112403 to "Rare",
            112404 to "Epic",
            112501 to "Epic",
            113101 to "Common",
            113102 to "Common",
            113104 to "Common",
            113201 to "Uncommon",
            113202 to "Rare",
            113203 to "Uncommon",
            113205 to "Uncommon",
            113206 to "Uncommon",
            113207 to "Rare",
            113301 to "Rare",
            113302 to "Rare",
            113401 to "Epic",
            113402 to "Rare",
            113403 to "Rare",
            113404 to "Rare",
            113405 to "Epic",
            113406 to "Epic",
            113408 to "Epic",
            113409 to "Epic",
            113410 to "Epic",
            113411 to "Epic",
            113412 to "Epic",
            113501 to "Legend",
            113502 to "Legend",
            114101 to "Common",
            114201 to "Uncommon",
            114202 to "Uncommon",
            114203 to "Rare",
            114301 to "Rare",
            114302 to "Rare",
            114303 to "Rare",
            114304 to "Rare",
            114401 to "Epic",
            114402 to "Rare",
            114403 to "Epic",
            114405 to "Epic",
            114501 to "Epic",
            114502 to "Legend",
            115101 to "Common",
            115201 to "Uncommon",
            115202 to "Uncommon",
            115301 to "Rare",
            115302 to "Rare",
            115303 to "Rare",
            115401 to "Rare",
            115402 to "Epic",
            115403 to "Epic",
            115404 to "Epic",
            115405 to "Epic",
            115501 to "Legend",
            116101 to "Common",
            116201 to "Uncommon",
            116202 to "Uncommon",
            116301 to "Rare",
            116401 to "Rare",
            116402 to "Rare",
            116403 to "Epic",
            116404 to "Epic",
            116405 to "Epic",
            116406 to "Epic",
            116501 to "Legend",
            117101 to "Common",
            117201 to "Uncommon",
            117301 to "Rare",
            117401 to "Rare",
            117402 to "Epic",
            117403 to "Epic",
            117404 to "Epic",
            117405 to "Epic",
            118101 to "Common",
            118201 to "Uncommon",
            118301 to "Rare",
            118401 to "Rare",
            118402 to "Rare",
            118403 to "Epic",
            118404 to "Epic",
            118405 to "Epic",
            118406 to "Epic",
            118501 to "Legend",
            119101 to "Common",
            119201 to "Uncommon",
            119301 to "Rare",
            119302 to "Rare",
            119401 to "Epic",
            119402 to "Epic",
            120101 to "Common",
            120201 to "Uncommon",
            120301 to "Rare",
            120302 to "Epic",
            120401 to "Epic",
            120402 to "Legend",
            120403 to "Epic",
            120404 to "Epic",
            120405 to "Epic",
            121101 to "Common",
            121201 to "Uncommon",
            121202 to "Uncommon",
            121301 to "Rare",
            121302 to "Rare",
            121303 to "Rare",
            121304 to "Rare",
            121305 to "Rare",
            121306 to "Rare",
            121401 to "Epic",
            121402 to "Epic",
            121403 to "Epic",
            121404 to "Epic",
            121405 to "Epic",
            121406 to "Epic",
            121407 to "Epic",
            201101 to "Common",
            201102 to "Common",
            201104 to "Common",
            201201 to "Uncommon",
            201202 to "Uncommon",
            201203 to "Uncommon",
            201204 to "Uncommon",
            201205 to "Uncommon",
            201301 to "Rare",
            201302 to "Rare",
            201303 to "Rare",
            201401 to "Rare",
            201402 to "Rare",
            201403 to "Epic",
            201404 to "Epic",
            201405 to "Rare",
            201406 to "Epic",
            201407 to "Epic",
            201408 to "Legend",
            201409 to "Epic",
            201410 to "Epic",
            201411 to "Epic",
            202101 to "Common",
            202103 to "Common",
            202105 to "Common",
            202106 to "Common",
            202201 to "Uncommon",
            202202 to "Uncommon",
            202203 to "Uncommon",
            202205 to "Uncommon",
            202206 to "Uncommon",
            202207 to "Uncommon",
            202209 to "Uncommon",
            202210 to "Uncommon",
            202301 to "Rare",
            202302 to "Rare",
            202303 to "Rare",
            202304 to "Rare",
            202305 to "Rare",
            202306 to "Rare",
            202401 to "Rare",
            202402 to "Rare",
            202404 to "Rare",
            202405 to "Epic",
            202406 to "Epic",
            202407 to "Epic",
            202408 to "Epic",
            202410 to "Epic",
            202411 to "Epic",
            202412 to "Epic",
            202413 to "Epic",
            202415 to "Epic",
            202416 to "Legend",
            202417 to "Epic",
            202501 to "Legend",
            202502 to "Legend",
            203101 to "Common",
            203102 to "Common",
            203104 to "Common",
            203201 to "Uncommon",
            203202 to "Uncommon",
            203203 to "Uncommon",
            203204 to "Uncommon",
            203301 to "Rare",
            203302 to "Rare",
            203303 to "Rare",
            203304 to "Rare",
            203401 to "Rare",
            203402 to "Epic",
            203403 to "Epic",
            203404 to "Epic",
            203405 to "Epic",
            203406 to "Epic",
            203407 to "Epic",
            203408 to "Rare",
            203409 to "Epic",
            203410 to "Epic",
            203501 to "Legend",
            203502 to "Legend",
            203503 to "Legend",
            204101 to "Common",
            204102 to "Common",
            204103 to "Common",
            204201 to "Uncommon",
            204202 to "Uncommon",
            204203 to "Uncommon",
            204204 to "Uncommon",
            204301 to "Uncommon",
            204302 to "Rare",
            204401 to "Rare",
            204402 to "Epic",
            204403 to "Rare",
            204404 to "Rare",
            204405 to "Rare",
            204406 to "Rare",
            204407 to "Epic",
            204408 to "Epic",
            204409 to "Legend",
            204411 to "Epic",
            204410 to "Legend",
            204501 to "Legend",
            204502 to "Legend",
            205101 to "Common",
            205102 to "Common",
            205103 to "Common",
            205105 to "Common",
            205106 to "Common",
            205107 to "Common",
            205108 to "Common",
            205109 to "Common",
            205110 to "Common",
            205202 to "Uncommon",
            205203 to "Uncommon",
            205204 to "Uncommon",
            205205 to "Uncommon",
            205206 to "Uncommon",
            205207 to "Uncommon",
            205208 to "Uncommon",
            205209 to "Uncommon",
            205210 to "Uncommon",
            205211 to "Uncommon",
            205212 to "Uncommon",
            205301 to "Rare",
            205302 to "Rare",
            205303 to "Rare",
            205201 to "Rare",
            205401 to "Rare",
            205304 to "Rare",
            205305 to "Rare",
            205306 to "Rare",
            205404 to "Rare",
            205405 to "Rare",
            205402 to "Epic",
            205403 to "Epic",
            205501 to "Legend"
    )
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
        val image1:ImageView = itemView.findViewById(R.id.item1)
        val image2:ImageView = itemView.findViewById(R.id.item2)
        val image3:ImageView = itemView.findViewById(R.id.item3)
        val image4:ImageView = itemView.findViewById(R.id.item4)
        val image5:ImageView = itemView.findViewById(R.id.item5)
        val image6:ImageView = itemView.findViewById(R.id.item6)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.rankNumber.text = "#${recordList[position].gameRank.toString()}"
        holder.mode.text = mode(recordList[position].seasonId, recordList[position].matchingTeamMode)
        holder.date.text = date(recordList[position].startDtm)
        val charNumber = recordList[position].characterNum
        holder.charImage.setImageResource( charImageArray[ charNumber - 1] )
        holder.charName.text = charName[ charNumber - 1]
        holder.level.text = recordList[position].characterLevel.toString()
        holder.kda.text = "${recordList[position].playerKill} / ${recordList[position].monsterKill} / ${recordList[position].playerAssistant}"
        itemImageMap[ recordList[position].equipment["0"] ]?.let {
            holder.image1.apply {
                setImageResource(it)
                clipToOutline = true
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].equipment["0"] ]) )
            }
        }
        itemImageMap[ recordList[position].equipment["1"] ]?.let {
            holder.image2.apply {
                setImageResource(it)
                clipToOutline = true
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].equipment["1"] ]) )
            }
        }
        itemImageMap[ recordList[position].equipment["2"] ]?.let {
            holder.image3.apply {
                setImageResource(it)
                clipToOutline = true
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].equipment["2"] ]) )
            }
        }
        itemImageMap[ recordList[position].equipment["3"] ]?.let {
            holder.image4.apply {
                setImageResource(it)
                clipToOutline = true
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].equipment["3"] ]) )
            }
        }
        itemImageMap[ recordList[position].equipment["4"] ]?.let {
            holder.image5.apply {
                setImageResource(it)
                clipToOutline = true
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].equipment["4"] ]) )
            }
        }
        itemImageMap[ recordList[position].equipment["5"] ]?.let {
            holder.image6.apply {
                setImageResource(it)
                clipToOutline = true
                backgroundTintList = ColorStateList.valueOf( color( itemGrade[ recordList[position].equipment["5"] ]) )
            }
        }
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

    fun color(s: String?): Int {
        return when(s){
            "Common" -> Color.WHITE
            "Uncommon" -> Color.parseColor("#6DE7AB")
            "Rare" -> Color.parseColor("#7583FF")
            "Epic" -> Color.parseColor("#BB73FF")
            else -> Color.parseColor("#FEFF91")
        }
    }
}