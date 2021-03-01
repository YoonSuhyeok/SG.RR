package com.sg.sgrr.Activity

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sg.sgrr.Fragment.TotalSummaryFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.sg.sgrr.Adapter.VerticalSpaceItemDecoration
import com.sg.sgrr.Adapter.ResultRecordAdapter
import com.sg.sgrr.Etc.Items.Companion.charImageArray
import com.sg.sgrr.Etc.Items.Companion.charNameArray
import com.sg.sgrr.Etc.Items.Companion.charProfileArray
import com.sg.sgrr.R
import com.sg.sgrr.Retrofit.*
import com.sg.sgrr.DBHelper.DBHelper
import com.sg.sgrr.Model.Record
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordActivity: AppCompatActivity() {

    private val stats = ArrayList<Stats>()
    private val top3CharArray = Array(3) {Array(3){ CharacterStats(0, 0, 0, 0, 0, 0, 0) } }
    private var recordAdapter: ResultRecordAdapter = ResultRecordAdapter(ArrayList())
    private var recordList:ArrayList<RecordElement> = ArrayList()
    private val recyclerview: RecyclerView by lazy { findViewById(R.id.score) }
    private var nickname:String = ""
    private var rank:String = ""
    private var rankNumber = ""
    private var rankPercent = ""
    private var mostChar1:Int = 0
    private var mostChar2:Int = 0
    private var mostChar3:Int = 0
    private var mostPercent1:Int = 0
    private var mostPercent2:Int = 0
    private var mostPercent3:Int = 0
    private val userNumber by lazy { intent.getIntExtra("UserNumber", 0).toString() }
    private lateinit var blackDB: DBHelper

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_result)

        blackDB = DBHelper(this)

        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }

        nickname = intent.getStringExtra("UserNickname").toString()
        findViewById<TextView>(R.id.profile_name).text = nickname
        if (userNumber != "0") {
            Retrofits.client.getUserStats(userNumber, "1").enqueue(object : Callback<Stats> {
                override fun onResponse(call: Call<Stats>, response: Response<Stats>) {
                    // 비주얼 UI 조작
                    stats.add(response.body() as Stats)
                    uiSomething()
                }

                override fun onFailure(call: Call<Stats>, t: Throwable) {
                }
            })
        }

        val spaceDecoration = VerticalSpaceItemDecoration(10)
        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RecordActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(spaceDecoration)
        }

        Retrofits.client.getUserGames(userNumber).enqueue(object : Callback<Games> {
            override fun onResponse(call: Call<Games>, response: Response<Games>) {
                if (response.body()?.userGames != null) {
                    var lists = response.body()!!.userGames as MutableList<UserGames>

                    val soloNormal  = calcMMR(lists.filter { it.seasonId == 0 && it.matchingTeamMode == 1} as MutableList<UserGames>)
                    val duoNormal   = calcMMR(lists.filter { it.seasonId == 0 && it.matchingTeamMode == 2} as MutableList<UserGames>)
                    val squadNormal = calcMMR(lists.filter { it.seasonId == 0 && it.matchingTeamMode == 3} as MutableList<UserGames>)
                    val soloRank    = calcMMR(lists.filter { it.seasonId == 1 && it.matchingTeamMode == 1} as MutableList<UserGames>)
                    val duoRank     = calcMMR(lists.filter { it.seasonId == 1 && it.matchingTeamMode == 2} as MutableList<UserGames>)
                    val squadRank   = calcMMR(lists.filter { it.seasonId == 1 && it.matchingTeamMode == 3} as MutableList<UserGames>)
                    var list = ArrayList<RecordElement>()
                    soloNormal.forEach { list.add(it) }
                    duoNormal.forEach { list.add(it) }
                    squadNormal.forEach { list.add(it) }
                    soloRank.forEach { list.add(it) }
                    duoRank.forEach { list.add(it) }
                    squadRank.forEach { list.add(it) }
                    
                    recordAdapter = ResultRecordAdapter(list)

                    recyclerview.adapter = recordAdapter
                    recordList = list
                    findViewById<ConstraintLayout>(R.id.loding).visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<Games>, t: Throwable) {}
        })

    }

    private fun calcMMR(List: MutableList<UserGames>): ArrayList<RecordElement> {
        val rankElements = ArrayList<RecordElement>()
        if(List.size > 0){
            rankElements.add( RecordElement( List[0], stats[0].UserStats[0].mmr - List[0].mmrBefore ))
            for( i in 1 until List.size) {
                rankElements.add(RecordElement(List[i], List[i - 1].mmrBefore - List[i].mmrBefore))
            }
        }
        return rankElements
    }

    private fun setBottomBtn() {
        val bottomTotal = findViewById<TextView>(R.id.recycleTotal)
        val bottomSolo = findViewById<TextView>(R.id.recycleSolo)
        val bottomDuo = findViewById<TextView>(R.id.recycleDuo)
        val bottomSquad = findViewById<TextView>(R.id.recycleSquad)

        val bottomBarTotal = findViewById<ImageView>(R.id.bottom_total_underbar)
        val bottomBarSolo = findViewById<ImageView>(R.id.bottom_solo_underbar)
        val bottomBarDuo = findViewById<ImageView>(R.id.bottom_duo_underbar)
        val bottomBarSquad = findViewById<ImageView>(R.id.bottom_squad_underbar)

        bottomTotal.setOnClickListener {
            recyclerview.adapter = ResultRecordAdapter(recordList)

            bottomTotal.setTextColor(Color.parseColor("#E8B32C"))
            bottomSolo.setTextColor(Color.parseColor("#5A5858"))
            bottomDuo.setTextColor(Color.parseColor("#5A5858"))
            bottomSquad.setTextColor(Color.parseColor("#5A5858"))
            bottomBarTotal.visibility = View.VISIBLE
            bottomBarSolo.visibility = View.INVISIBLE
            bottomBarDuo.visibility = View.INVISIBLE
            bottomBarSquad.visibility = View.INVISIBLE
        }

        bottomSolo.setOnClickListener {
            recyclerview.adapter = ResultRecordAdapter(recordList.filter { it.userGame.matchingTeamMode == 1 } as MutableList<RecordElement>)

            bottomTotal.setTextColor(Color.parseColor("#5A5858"))
            bottomSolo.setTextColor(Color.parseColor("#E8B32C"))
            bottomDuo.setTextColor(Color.parseColor("#5A5858"))
            bottomSquad.setTextColor(Color.parseColor("#5A5858"))
            bottomBarTotal.visibility = View.INVISIBLE
            bottomBarSolo.visibility = View.VISIBLE
            bottomBarDuo.visibility = View.INVISIBLE
            bottomBarSquad.visibility = View.INVISIBLE
        }

        bottomDuo.setOnClickListener {
            recyclerview.adapter = ResultRecordAdapter(recordList.filter { it.userGame.matchingTeamMode == 2 } as MutableList<RecordElement>)

            bottomTotal.setTextColor(Color.parseColor("#5A5858"))
            bottomSolo.setTextColor(Color.parseColor("#5A5858"))
            bottomDuo.setTextColor(Color.parseColor("#E8B32C"))
            bottomSquad.setTextColor(Color.parseColor("#5A5858"))
            bottomBarTotal.visibility = View.INVISIBLE
            bottomBarSolo.visibility = View.INVISIBLE
            bottomBarDuo.visibility = View.VISIBLE
            bottomBarSquad.visibility = View.INVISIBLE
        }

        bottomSquad.setOnClickListener {

            recyclerview.adapter = ResultRecordAdapter(recordList.filter { it.userGame.matchingTeamMode == 3 } as MutableList<RecordElement>)

            bottomTotal.setTextColor(Color.parseColor("#5A5858"))
            bottomSolo.setTextColor(Color.parseColor("#5A5858"))
            bottomDuo.setTextColor(Color.parseColor("#5A5858"))
            bottomSquad.setTextColor(Color.parseColor("#E8B32C"))
            bottomBarTotal.visibility = View.INVISIBLE
            bottomBarSolo.visibility = View.INVISIBLE
            bottomBarDuo.visibility = View.INVISIBLE
            bottomBarSquad.visibility = View.VISIBLE
        }
    }

    // UI 변경 함수 (여기에서 인터페이스 수정)
    fun uiSomething(){
        // 1. 캐릭터 별 데이터를 담은 2차원 배열 = charData
        val charData = calc_mostChar()

        // 2. 랭크 계산
        calc_Rank()

        // 3.
        setCenterBtn()
        setBottomBtn()

        val r = Runnable {
            if(blackDB.getRecord(nickname) == 0){
                blackDB.addRecord(
                        Record(nickname, rank, rankNumber,
                                rankPercent, mostChar1, mostChar2, mostChar3,
                                mostPercent1, mostPercent2, mostPercent3,
                                userNumber.toInt())
                )
            } else {
                blackDB.updateRecord(
                        nickname,
                        Record(nickname, rank, rankNumber,
                                rankPercent, mostChar1, mostChar2, mostChar3,
                                mostPercent1, mostPercent2, mostPercent3,
                        userNumber.toInt())
                )
            }
        }

        val thread = Thread(r)
        thread.start()
    }

    // most 캐릭터 계산
    private fun calc_mostChar(): Array<Array<CharacterStats>> {
        val totalCharacter = Array(3) { Array(charNameArray.size) { i -> CharacterStats(i, 0, 0, 0, 0, 0, 0) } }
        var mostChar = 0
        var mostTotalGames = 0
        // 솔로
        for( x in stats[0].UserStats[0].characterStats){
            totalCharacter[0][x.characterCode].apply {
                characterCode = x.characterCode
                totalGames = x.totalGames
                maxKillings = x.maxKillings
                top3 = x.top3
                top3Rate = x.top3Rate
                averageRank = x.averageRank
                wins = x.wins
            }

            if( mostTotalGames < x.totalGames ){
                mostTotalGames = x.totalGames
                mostChar = x.characterCode
            }
            // 솔로 모스트3 계산
            if( top3CharArray[0][2].totalGames < x.totalGames){
                if(top3CharArray[0][1].totalGames < x.totalGames){
                    // 모스트 1인 경우
                    if(top3CharArray[0][0].totalGames < x.totalGames){
                        top3CharArray[0][0].apply {
                            characterCode = x.characterCode
                            totalGames = x.totalGames
                            maxKillings = x.maxKillings
                            top3 = x.top3
                            top3Rate = x.top3Rate
                            averageRank = x.averageRank
                            wins = x.wins
                        }
                    }
                    // 모스트 2인 경우
                    else{
                        top3CharArray[0][1].apply {
                            characterCode = x.characterCode
                            totalGames = x.totalGames
                            maxKillings = x.maxKillings
                            top3 = x.top3
                            top3Rate = x.top3Rate
                            averageRank = x.averageRank
                            wins = x.wins
                        }
                    }
                }
                // 모스트 3인 경우
                else{
                    top3CharArray[0][2].apply {
                        characterCode = x.characterCode
                        totalGames = x.totalGames
                        maxKillings = x.maxKillings
                        top3 = x.top3
                        top3Rate = x.top3Rate
                        averageRank = x.averageRank
                        wins = x.wins
                    }
                }
            }
        }
        // 듀오
        for( x in stats[0].UserStats[1].characterStats){
            totalCharacter[1][x.characterCode].apply {
                characterCode = x.characterCode
                totalGames = x.totalGames
                maxKillings = x.maxKillings
                top3 = x.top3
                top3Rate = x.top3Rate
                averageRank = x.averageRank
                wins = x.wins
            }
            if( mostTotalGames < x.totalGames ){
                mostTotalGames = x.totalGames
                mostChar = x.characterCode
            }
            // 듀오 모스트3 계산
            if( top3CharArray[1][2].totalGames < x.totalGames){
                if(top3CharArray[1][1].totalGames < x.totalGames){
                    // 모스트 1인 경우
                    if(top3CharArray[1][0].totalGames < x.totalGames){
                        top3CharArray[1][0].apply {
                            characterCode = x.characterCode
                            totalGames = x.totalGames
                            maxKillings = x.maxKillings
                            top3 = x.top3
                            top3Rate = x.top3Rate
                            averageRank = x.averageRank
                            wins = x.wins
                        }
                    }
                    // 모스트 2인 경우
                    else{
                        top3CharArray[1][1].apply {
                            characterCode = x.characterCode
                            totalGames = x.totalGames
                            maxKillings = x.maxKillings
                            top3 = x.top3
                            top3Rate = x.top3Rate
                            averageRank = x.averageRank
                            wins = x.wins
                        }
                    }
                }
                // 모스트 3인 경우
                else{
                    top3CharArray[1][2].apply {
                        characterCode = x.characterCode
                        totalGames = x.totalGames
                        maxKillings = x.maxKillings
                        top3 = x.top3
                        top3Rate = x.top3Rate
                        averageRank = x.averageRank
                        wins = x.wins
                    }
                }
            }
        }
        // 스쿼드
        for( x in stats[0].UserStats[2].characterStats){
            totalCharacter[2][x.characterCode].apply {
                characterCode = x.characterCode
                totalGames = x.totalGames
                maxKillings = x.maxKillings
                top3 = x.top3
                top3Rate = x.top3Rate
                averageRank = x.averageRank
                wins = x.wins
            }
            if( mostTotalGames < x.totalGames ){
                mostTotalGames = x.totalGames
                mostChar = x.characterCode
            }
            // 스쿼드 모스트3 계산
            if( top3CharArray[2][2].totalGames < x.totalGames){
                if(top3CharArray[2][1].totalGames < x.totalGames){
                    // 모스트 1인 경우
                    if(top3CharArray[2][0].totalGames < x.totalGames){
                        top3CharArray[2][0].apply {
                            characterCode = x.characterCode
                            totalGames = x.totalGames
                            maxKillings = x.maxKillings
                            top3 = x.top3
                            top3Rate = x.top3Rate
                            averageRank = x.averageRank
                            wins = x.wins
                        }
                    }
                    // 모스트 2인 경우
                    else{
                        top3CharArray[2][1].apply {
                            characterCode = x.characterCode
                            totalGames = x.totalGames
                            maxKillings = x.maxKillings
                            top3 = x.top3
                            top3Rate = x.top3Rate
                            averageRank = x.averageRank
                            wins = x.wins
                        }
                    }
                }
                // 모스트 3인 경우
                else{
                    top3CharArray[2][2].apply {
                        characterCode = x.characterCode
                        totalGames = x.totalGames
                        maxKillings = x.maxKillings
                        top3 = x.top3
                        top3Rate = x.top3Rate
                        averageRank = x.averageRank
                        wins = x.wins
                    }
                }
            }
        }

        if (top3CharArray[0][0].characterCode-1 == -1) {
            mostChar1 = 0
        } else {
            mostChar1 = mostChar
            mostPercent1 = top3CharArray[0][0].averageRank
        }
        if (top3CharArray[0][1].characterCode-1 == -1) {
            mostChar2 = 0
        } else {
            mostChar2 = top3CharArray[0][1].characterCode
            mostPercent2 = top3CharArray[0][1].averageRank
        }
        if (top3CharArray[0][2].characterCode-1 == -1) {
            mostChar3 = 0
        } else {
            top3CharArray[0][2].characterCode
            mostPercent3 = top3CharArray[0][2].averageRank
        }

        mostPercent1 = if(top3CharArray[0][0].totalGames == 0){
             0
        } else {
            top3CharArray[0][1].averageRank
        }
        mostPercent2 = if(top3CharArray[0][1].totalGames == 0){
            0
        } else {
            top3CharArray[0][1].averageRank
        }
        mostPercent3 = if(top3CharArray[0][2].totalGames == 0){
            0
        } else {
            top3CharArray[0][2].wins / top3CharArray[0][2].totalGames
        }

        // 프로필 캐릭터 사진 변경
        findViewById<ImageView>(R.id.profile_image).setImageResource(charProfileArray[mostChar-1])
        return totalCharacter
    }

    // Rank, 평균 등수, 티어표(Summary) 3가지
    private fun calc_Rank() {
        val soloMMR = stats[0].UserStats[0].mmr
        var soloRank = stats[0].UserStats[0].rank
        val soloRankSize = stats[0].UserStats[0].rankSize

        val duoMMR = stats[0].UserStats[1].mmr
        var duoRank = stats[0].UserStats[1].rank
        val duoRankSize = stats[0].UserStats[1].rankSize

        val squadMMR = stats[0].UserStats[2].mmr
        var squadRank = stats[0].UserStats[2].rank
        val squadRankSize = stats[0].UserStats[2].rankSize

        var maxMmr = if(soloMMR > duoMMR){
            if(soloMMR > squadMMR)
                soloMMR
            else
                squadMMR
        }else{
            if(duoMMR > squadMMR)
                duoMMR
            else
                squadMMR
        }

        if( soloRank  == 0 ) soloRank  = 100000
        if( duoRank   == 0 ) duoRank   = 100000
        if( squadRank == 0 ) squadRank = 100000
        val maxRank = if(soloRank < duoRank){
            if(soloRank < squadRank){
                rankPercent = stats[0].UserStats[0].rankPercent.toString()
                soloRank.toDouble()
            } else {
                rankPercent = stats[0].UserStats[1].rankPercent.toString()
                squadRank.toDouble()
            }
        } else if(duoRank < squadRank){
            rankPercent = stats[0].UserStats[1].rankPercent.toString()
            duoRank.toDouble()
        }else{
            rankPercent = stats[0].UserStats[2].rankPercent.toString()
            squadRank.toDouble()
        }

        /*if( soloMMR > duoMMR ){
            if( soloMMR > squadMMR){
                maxMmr = soloMMR
                maxRank = soloRank.toDouble()
                maxRankSize = soloRankSize.toDouble()
            } else {
                maxMmr = squadMMR
                maxRank = squadRank.toDouble()
                maxRankSize = squadRankSize.toDouble()
            }
        } else {
            if(duoMMR > squadMMR) {
                maxMmr = duoMMR
                maxRank = duoRank.toDouble()
                maxRankSize = duoRankSize.toDouble()
            } else {
                maxMmr = squadMMR
                maxRank = squadRank.toDouble()
                maxRankSize = squadRankSize.toDouble()
            }
        }*/

        // 프로필 랭킹
        rankNumber = maxRank.toString()
        if(maxRank == 0.0){
            findViewById<TextView>(R.id.profile_rank).text = String.format("최고 MMR의 배치 정보가 없습니다.")
            findViewById<TextView>(R.id.profile_rank_Percent).text = ""
        } else{
            findViewById<TextView>(R.id.profile_rank).text = "상위 ${rankNumber}위"
            findViewById<TextView>(R.id.profile_rank_Percent).text = "(상위 ${rankPercent}%)"
        }

        // 평균 등수
        val soloGrade = stats[0].UserStats[0].averageRank
        val duoGrade = stats[0].UserStats[1].averageRank
        val squadGrade = stats[0].UserStats[2].averageRank
        var averGrade = soloGrade
        var count = 1
        if(duoGrade != 0.0){
            averGrade += duoGrade
            count++
        }
        if(squadGrade != 0.0){
            averGrade += squadGrade
            count++
        }
        val profileAverRank = findViewById<TextView>(R.id.profile_averRank)
        val rank:Double = averGrade / count
        profileAverRank.text = String.format("#%.1f", rank)
        if(rank < 6) profileAverRank.setTextColor(Color.parseColor("#E8B32C"))
        else if(rank < 10) profileAverRank.setTextColor(Color.parseColor("#369FFF"))

        if(soloMMR > duoMMR){
            if(soloMMR > squadMMR) {
                this.rank = setTierName(soloMMR)
            } else {
                this.rank = setTierName(squadMMR)
            }
        } else if(duoMMR > squadMMR){
            this.rank = setTierName(duoMMR)
        } else {
            this.rank = setTierName(squadMMR)
        }

        findViewById<TextView>(R.id.soloTierName).text = setTierName(soloMMR)
        // Summary - 솔로 MMR 및 Tier 그림
        findViewById<TextView>(R.id.summary_txt_soloMMR).text = String.format("%d MMR", soloMMR)
        val summarySoloTier = findViewById<ImageView>(R.id.summary_soloTier)
        when (soloMMR) {
            in 0..1       -> summarySoloTier.setImageResource(R.drawable.tier_unrank)
            in 1..399     -> summarySoloTier.setImageResource(R.drawable.tier_iron)
            in 400..799   -> summarySoloTier.setImageResource(R.drawable.tier_bronze)
            in 800..1299  -> summarySoloTier.setImageResource(R.drawable.tier_silver)
            in 1300..1699 -> summarySoloTier.setImageResource(R.drawable.tier_gold)
            in 1700..2099 -> summarySoloTier.setImageResource(R.drawable.tier_platinum)
            in 2100..2499 -> summarySoloTier.setImageResource(R.drawable.tier_diamond)
            in 2500..2899 -> summarySoloTier.setImageResource(R.drawable.tier_demigod)
            else          -> summarySoloTier.setImageResource(R.drawable.tier_eternity)
        }
        findViewById<TextView>(R.id.duoTierName).text = setTierName(duoMMR)
        findViewById<TextView>(R.id.summary_txt_duoMMR).text = String.format("%d MMR", duoMMR)
        val summaryDuoTier = findViewById<ImageView>(R.id.summary_duoTier)
        when (duoMMR) {
            in 0..1       -> summaryDuoTier.setImageResource(R.drawable.tier_unrank)
            in 1..399     -> summaryDuoTier.setImageResource(R.drawable.tier_iron)
            in 400..799   -> summaryDuoTier.setImageResource(R.drawable.tier_bronze)
            in 800..1299  -> summaryDuoTier.setImageResource(R.drawable.tier_silver)
            in 1300..1699 -> summaryDuoTier.setImageResource(R.drawable.tier_gold)
            in 1700..2099 -> summaryDuoTier.setImageResource(R.drawable.tier_platinum)
            in 2100..2499 -> summaryDuoTier.setImageResource(R.drawable.tier_diamond)
            in 2500..2899 -> summaryDuoTier.setImageResource(R.drawable.tier_demigod)
            else -> summaryDuoTier.setImageResource(R.drawable.tier_eternity)
        }
        findViewById<TextView>(R.id.squadTierName).text = setTierName(squadMMR)
        findViewById<TextView>(R.id.summary_txt_squadMMR).text = String.format("%d MMR", squadMMR)
        val summarySquadTier = findViewById<ImageView>(R.id.summary_squadTier)
        when (squadMMR){
            in 0..1       -> summarySquadTier.setImageResource(R.drawable.tier_unrank)
            in 1..399     -> summarySquadTier.setImageResource(R.drawable.tier_iron)
            in 400..799   -> summarySquadTier.setImageResource(R.drawable.tier_bronze)
            in 800..1299  -> summarySquadTier.setImageResource(R.drawable.tier_silver)
            in 1300..1699 -> summarySquadTier.setImageResource(R.drawable.tier_gold)
            in 1700..2099 -> summarySquadTier.setImageResource(R.drawable.tier_platinum)
            in 2100..2499 -> summarySquadTier.setImageResource(R.drawable.tier_diamond)
            in 2500..2899 -> summarySquadTier.setImageResource(R.drawable.tier_demigod)
            else          -> summarySquadTier.setImageResource(R.drawable.tier_eternity)
        }
        return
    }

    private fun setTierName(mmr:Int): String {
        return when (mmr) {
            in 0..399 -> {
                when(mmr){
                    in 0..99 -> "아이언 IV"
                    in 100..199 -> "아이언 III"
                    in 200..299 -> "아이언 II"
                    else ->  "아이언 I"
                }
            }
            in 400..799 -> {
                when(mmr){
                    in 400..499 -> "브론즈 IV"
                    in 500..599 -> "브론즈 III"
                    in 600..699 -> "브론즈 II"
                    else ->  "브론즈 I"
                }
            }
            in 800..1299 -> {
                when(mmr){
                    in 800..999 -> "실버 IV"
                    in 900..999 -> "실버 III"
                    in 1000..1199 -> "실버 II"
                    else ->  "실버 I"
                }
            }
            in 1300..1699 -> {
                when(mmr){
                    in 1300..1399 -> "골드 IV"
                    in 1400..1499 -> "골드 III"
                    in 1500..1599 -> "골드 II"
                    else ->  "골드 I"
                }
            }
            in 1700..2099 -> {
                when(mmr){
                    in 1700..1799 -> "플래티넘 IV"
                    in 1800..1899 -> "플래티넘 III"
                    in 1900..1999 -> "플래티넘 II"
                    else ->  "플래티넘 I"
                }
            }
            in 2100..2499 -> {
                when(mmr){
                    in 2100..2199 -> "다이아 IV"
                    in 2200..2299 -> "다이아 III"
                    in 2300..2399 -> "다이아 II"
                    else ->  "다이아 I"
                }
            }
            in 2500..2899 -> {
                when(mmr){
                    in 2500..2599 -> "데미갓 IV"
                    in 2600..2699 -> "데미갓 III"
                    in 2700..2799 -> "데미갓 II"
                    else ->  "데미갓 I"
                }
            }
            else -> {
                when(mmr){
                    in 2900..2999 -> "이터니티 IV"
                    in 3000..3099 -> "이터니티 III"
                    in 3100..3199 -> "이터니티 II"
                    else ->  "이터니티 I"
                }
            }
        }
    }

    private fun setCenterBtn() {

        val totalBtnSolo = findViewById<TextView>(R.id.total_btn_solo)
        val totalBtnDuo = findViewById<TextView>(R.id.total_btn_duo)
        val totalBtnSquad = findViewById<TextView>(R.id.total_btn_squad)
        val centerSoloUnderBar = findViewById<ImageView>(R.id.center_solo_underbar)
        val centerDuoUnderBar = findViewById<ImageView>(R.id.center_duo_underbar)
        val centerSquadUnderBar = findViewById<ImageView>(R.id.center_squad_underbar)

        totalBtnSolo.setTextColor(Color.parseColor("#E8B32C"))
        centerDuoUnderBar.visibility = View.GONE
        centerSquadUnderBar.visibility = View.GONE

        val newFrag = TotalSummaryFragment()
        val bundle = Bundle().apply {
            putInt("char1_img", if(top3CharArray[0][0].characterCode>0){ charImageArray[top3CharArray[0][0].characterCode-1] } else{ -1 })
            putInt("char2_img", if(top3CharArray[0][1].characterCode>0){charImageArray[top3CharArray[0][1].characterCode-1]}else{-1})
            putInt("char3_img", if(top3CharArray[0][2].characterCode>0){charImageArray[top3CharArray[0][2].characterCode-1]}else{-1})
            putString("char1_name", if(top3CharArray[0][0].characterCode>0){charNameArray[top3CharArray[0][0].characterCode-1]}else{"정보없음"})
            putString("char2_name", if(top3CharArray[0][1].characterCode>0){charNameArray[top3CharArray[0][1].characterCode-1]}else{"정보없음"})
            putString("char3_name", if(top3CharArray[0][2].characterCode>0){charNameArray[top3CharArray[0][2].characterCode-1]}else{"정보없음"})
            putString("char1_avgRank", String.format("AVG %d %s", top3CharArray[0][0].averageRank, when(top3CharArray[0][0].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}))
            putString("char2_avgRank", String.format("AVG %d %s", top3CharArray[0][1].averageRank, when(top3CharArray[0][1].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}))
            putString("char3_avgRank", String.format("AVG %d %s", top3CharArray[0][2].averageRank, when(top3CharArray[0][2].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}))
            putString("char1_games", String.format("%d 게임", top3CharArray[0][0].totalGames))
            putString("char2_games", String.format("%d 게임", top3CharArray[0][1].totalGames))
            putString("char3_games", String.format("%d 게임", top3CharArray[0][2].totalGames))
        }

        newFrag.arguments = bundle

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.total_summary, newFrag).commit()

        val totalGame = findViewById<TextView>(R.id.totalGame)
        val kda = findViewById<TextView>(R.id.kda)
        val avgRank = findViewById<TextView>(R.id.avgRank)

        val pieChart: PieChart = findViewById(R.id.pieChart)
        pieChart.apply {
            setUsePercentValues(true)
            // 요약 설명 유무
            description.isEnabled = false
            // 차트 돌리면 돌아가는 속도
            dragDecelerationFrictionCoef = 1f
            // 그래프 가운데 Hole 유무
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)
            // 선명도 조정값
            transparentCircleRadius = 61f
            setExtraOffsets(-5f,0f,-5f,-10f)
        }

        totalGame.text = "총 " + stats[0].UserStats[0].totalGames.toString() + "판"
        kda.text = stats[0].UserStats[0].averageKills.toString() + "/" +
                stats[0].UserStats[0].averageHunts.toString() + "/" +
                stats[0].UserStats[0].averageAssistants
        avgRank.text = stats[0].UserStats[0].averageRank.toString()
        val yValues = ArrayList<PieEntry>()
        yValues.add(PieEntry(stats[0].UserStats[0].top5.toFloat()))
        yValues.add(PieEntry(1-stats[0].UserStats[0].top5.toFloat()))

        val dataSet = PieDataSet(yValues, "")
        // 데이터 사이 공간 너비 값
        dataSet.apply {
            sliceSpace = 1f
            selectionShift = 0f
            setColors( Color.parseColor("#6297FF"), Color.parseColor("#FF6262") )
        }

        val data = PieData(dataSet)
        data.setValueTextSize(10f)
        data.setValueTextColor(Color.WHITE)

        pieChart.data = data
        pieChart.invalidate()
        
        // total - solo 버튼 리스너
        totalBtnSolo.setOnClickListener {

            totalGame.text = getString(R.string.totalGame, stats[0].UserStats[0].totalGames)
            kda.text = stats[0].UserStats[0].averageKills.toString() + "/" +
                    stats[0].UserStats[0].averageHunts.toString() + "/" +
                    stats[0].UserStats[0].averageAssistants
            avgRank.text = stats[0].UserStats[0].averageRank.toString()
            val yValues = ArrayList<PieEntry>()
            yValues.add(PieEntry(stats[0].UserStats[0].top5.toFloat()))
            yValues.add(PieEntry(1-stats[0].UserStats[0].top5.toFloat()))

            val dataSet = PieDataSet(yValues, "")
            // 데이터 사이 공간 너비 값
            dataSet.apply {
                sliceSpace = 1f
                selectionShift = 0f
                setColors( Color.parseColor("#6297FF"), Color.parseColor("#FF6262") )
            }

            val data = PieData(dataSet)
            data.setValueTextSize(10f)
            data.setValueTextColor(Color.WHITE)

            pieChart.data = data
            pieChart.invalidate()

            val solo_charcode1 = top3CharArray[0][0].characterCode
            val solo_charcode2 = top3CharArray[0][1].characterCode
            val solo_charcode3 = top3CharArray[0][2].characterCode

            if(solo_charcode1 != 0)
                newFrag.changeC1(
                        charImageArray[solo_charcode1-1],
                        charNameArray[solo_charcode1-1],
                        String.format("AVG %d %s", top3CharArray[0][0].averageRank, when(top3CharArray[0][0].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[0][0].totalGames)
                )
            else newFrag.inVisibleC1()

            if(solo_charcode2 != 0)
                newFrag.changeC2(
                        charImageArray[solo_charcode2-1],
                        charNameArray[solo_charcode2-1],
                        String.format("AVG %d %s", top3CharArray[0][1].averageRank, when(top3CharArray[0][1].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[0][1].totalGames)
                )
            else newFrag.inVisibleC2()

            if(solo_charcode3 != 0)
                newFrag.changeC3(
                        charImageArray[solo_charcode3-1],
                        charNameArray[solo_charcode3-1],
                        String.format("AVG %d %s", top3CharArray[0][2].averageRank, when(top3CharArray[0][2].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[0][2].totalGames)
                )
            else newFrag.inVisibleC3()

            totalBtnSolo.setTypeface(null, Typeface.BOLD)
            totalBtnDuo.setTypeface(null, Typeface.NORMAL)
            totalBtnSquad.setTypeface(null, Typeface.NORMAL)
            totalBtnSolo.setTextColor(Color.parseColor("#E8B32C"))
            totalBtnDuo.setTextColor(Color.parseColor("#5A5858"))
            totalBtnSquad.setTextColor(Color.parseColor("#5A5858"))
            centerSoloUnderBar.visibility = View.VISIBLE
            centerDuoUnderBar.visibility = View.GONE
            centerSquadUnderBar.visibility = View.GONE
        }

        // total - duo 버튼 리스너
        totalBtnDuo.setOnClickListener {

            totalGame.text = getString(R.string.totalGame, stats[0].UserStats[1].totalGames)
            kda.text = stats[0].UserStats[1].averageKills.toString() + "/" +
                    stats[0].UserStats[1].averageHunts.toString() + "/" +
                    stats[0].UserStats[1].averageAssistants
            avgRank.text = stats[0].UserStats[1].averageRank.toString()
            val yValues = ArrayList<PieEntry>()
            yValues.add(PieEntry(stats[0].UserStats[1].top5.toFloat()))
            yValues.add(PieEntry(1-stats[0].UserStats[1].top5.toFloat()))

            val dataSet = PieDataSet(yValues, "")
            // 데이터 사이 공간 너비 값
            dataSet.apply {
                sliceSpace = 1f
                selectionShift = 0f
                setColors( Color.parseColor("#6297FF"), Color.parseColor("#FF6262") )
            }

            val data = PieData(dataSet)
            data.setValueTextSize(10f)
            data.setValueTextColor(Color.WHITE)

            pieChart.data = data
            pieChart.invalidate()

            val duo_charcode1 = top3CharArray[1][0].characterCode
            val duo_charcode2 = top3CharArray[1][1].characterCode
            val duo_charcode3 = top3CharArray[1][2].characterCode

            if(duo_charcode1 != 0)
                newFrag.changeC1(
                        charImageArray[duo_charcode1-1],
                        charNameArray[duo_charcode1-1],
                        String.format("AVG %d %s", top3CharArray[1][0].averageRank, when(top3CharArray[1][0].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[1][0].totalGames)
                )
            else newFrag.inVisibleC1()

            if(duo_charcode2 != 0)
                newFrag.changeC2(
                        charImageArray[duo_charcode2-1],
                        charNameArray[duo_charcode2-1],
                        String.format("AVG %d %s", top3CharArray[1][1].averageRank, when(top3CharArray[1][1].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[1][1].totalGames)
                )
            else newFrag.inVisibleC2()

            if(duo_charcode3 != 0)
                newFrag.changeC3(
                        charImageArray[duo_charcode3-1],
                        charNameArray[duo_charcode3-1],
                        String.format("AVG %d %s", top3CharArray[1][2].averageRank, when(top3CharArray[1][2].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[1][2].totalGames)
                )
            else newFrag.inVisibleC3()

            totalBtnSolo.setTypeface(null, Typeface.NORMAL)
            totalBtnDuo.setTypeface(null, Typeface.BOLD)
            totalBtnSquad.setTypeface(null, Typeface.NORMAL)
            totalBtnSolo.setTextColor(Color.parseColor("#5A5858"))
            totalBtnDuo.setTextColor(Color.parseColor("#E8B32C"))
            totalBtnSquad.setTextColor(Color.parseColor("#5A5858"))
            centerSoloUnderBar.visibility = View.GONE
            centerDuoUnderBar.visibility = View.VISIBLE
            centerSquadUnderBar.visibility = View.GONE
        }

        // total - squad 버튼 리스너
        totalBtnSquad.setOnClickListener {

            totalGame.text = getString(R.string.totalGame, stats[0].UserStats[2].totalGames)
            kda.text = stats[0].UserStats[2].averageKills.toString() + "/" +
                    stats[0].UserStats[2].averageHunts.toString() + "/" +
                    stats[0].UserStats[2].averageAssistants
            avgRank.text = stats[0].UserStats[2].averageRank.toString()
            val yValues = ArrayList<PieEntry>()
            yValues.add(PieEntry(stats[0].UserStats[2].top5.toFloat()))
            yValues.add(PieEntry(1-stats[0].UserStats[2].top5.toFloat()))

            val dataSet = PieDataSet(yValues, "")
            // 데이터 사이 공간 너비 값
            dataSet.apply {
                sliceSpace = 1f
                selectionShift = 0f
                setColors( Color.parseColor("#6297FF"), Color.parseColor("#FF6262") )
            }

            val data = PieData(dataSet)
            data.setValueTextSize(10f)
            data.setValueTextColor(Color.WHITE)

            pieChart.data = data
            pieChart.invalidate()

            totalGame.text = "총 " + stats[0].UserStats[2].totalGames.toString() + "판"
            kda.text = stats[0].UserStats[2].averageKills.toString() + "/" +
                    stats[0].UserStats[2].averageHunts.toString() + "/" +
                    stats[0].UserStats[2].averageAssistants
            avgRank.text = stats[0].UserStats[2].averageRank.toString()

            val squad_charcode1 = top3CharArray[2][0].characterCode
            val squad_charcode2 = top3CharArray[2][1].characterCode
            val squad_charcode3 = top3CharArray[2][2].characterCode

            if(squad_charcode1 != 0)
                newFrag.changeC1(
                        charImageArray[squad_charcode1-1],
                        charNameArray[squad_charcode1-1],
                        String.format("AVG %d %s", top3CharArray[2][0].averageRank, when(top3CharArray[2][0].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[2][0].totalGames)
                )
            else newFrag.inVisibleC1()

            if (squad_charcode2 != 0)
                newFrag.changeC2(
                        charImageArray[squad_charcode2-1],
                        charNameArray[squad_charcode2-1],
                        String.format("AVG %d %s", top3CharArray[2][1].averageRank, when(top3CharArray[2][1].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[2][1].totalGames)
                )
            else newFrag.inVisibleC2()

            if (squad_charcode3 != 0)
                newFrag.changeC3(
                        charImageArray[squad_charcode3-1],
                        charNameArray[squad_charcode3-1],
                        String.format("AVG %d %s", top3CharArray[2][2].averageRank, when(top3CharArray[2][2].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[2][2].totalGames)
                )
            else newFrag.inVisibleC3()

            totalBtnSolo.setTypeface(null, Typeface.NORMAL)
            totalBtnDuo.setTypeface(null, Typeface.NORMAL)
            totalBtnSquad.setTypeface(null, Typeface.BOLD)
            totalBtnSolo.setTextColor(Color.parseColor("#5A5858"))
            totalBtnDuo.setTextColor(Color.parseColor("#5A5858"))
            totalBtnSquad.setTextColor(Color.parseColor("#E8B32C"))
            centerSoloUnderBar.visibility = View.GONE
            centerDuoUnderBar.visibility = View.GONE
            centerSquadUnderBar.visibility = View.VISIBLE
        }

    }
}