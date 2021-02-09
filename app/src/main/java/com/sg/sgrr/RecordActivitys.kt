package com.sg.sgrr

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sg.sgrr.Retrofit.BsAPI
import com.sg.sgrr.Retrofit.characterStats
import com.sg.sgrr.Retrofit.stats
import com.sg.sgrr.fragment.total_summary_fragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecordActivitys: AppCompatActivity() {

    val stats = ArrayList<stats>()
    val charImageArray = ArrayList<Int>()
    val charProfileArray = ArrayList<Int>()
    val charNameArray = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.record_result_activity)

        // 캐릭터 이미지(Profile) Array
        charProfileArray.add(R.drawable.char_1profile)
        charProfileArray.add(R.drawable.char_2profile)
        charProfileArray.add(R.drawable.char_3profile)
        charProfileArray.add(R.drawable.char_4profile)
        charProfileArray.add(R.drawable.char_5profile)
        charProfileArray.add(R.drawable.char_6profile)
        charProfileArray.add(R.drawable.char_7profile)
        charProfileArray.add(R.drawable.char_8profile)
        charProfileArray.add(R.drawable.char_9profile)
        charProfileArray.add(R.drawable.char_10profile)
        charProfileArray.add(R.drawable.char_11profile)
        charProfileArray.add(R.drawable.char_12profile)
        charProfileArray.add(R.drawable.char_13profile)
        charProfileArray.add(R.drawable.char_14profile)
        charProfileArray.add(R.drawable.char_15profile)
        charProfileArray.add(R.drawable.char_16profile)
        charProfileArray.add(R.drawable.char_17profile)
        charProfileArray.add(R.drawable.char_18profile)
        charProfileArray.add(R.drawable.char_19profile)
        //charProfileArray.add(R.drawable.char_20profile)
        //charProfileArray.add(R.drawable.char_21profile)

        // 캐릭터 이미지(Portrait) Array
        charImageArray.add(R.drawable.char_1portrait)
        charImageArray.add(R.drawable.char_2portrait)
        charImageArray.add(R.drawable.char_3portrait)
        charImageArray.add(R.drawable.char_4portrait)
        charImageArray.add(R.drawable.char_5portrait)
        charImageArray.add(R.drawable.char_6portrait)
        charImageArray.add(R.drawable.char_7portrait)
        charImageArray.add(R.drawable.char_8portrait)
        charImageArray.add(R.drawable.char_9portrait)
        charImageArray.add(R.drawable.char_10portrait)
        charImageArray.add(R.drawable.char_11portrait)
        charImageArray.add(R.drawable.char_12portrait)
        charImageArray.add(R.drawable.char_13portrait)
        charImageArray.add(R.drawable.char_14portrait)
        charImageArray.add(R.drawable.char_15portrait)
        charImageArray.add(R.drawable.char_16portrait)
        charImageArray.add(R.drawable.char_17portrait)
        charImageArray.add(R.drawable.char_18portrait)
        charImageArray.add(R.drawable.char_19portrait)
        //charImageArray.add(R.drawable.char_20portrait)
        //charImageArray.add(R.drawable.char_21portrait)

        // 캐릭터 이름 Array
        charNameArray.add("재키")
        charNameArray.add("아야")
        charNameArray.add("피오라")
        charNameArray.add("매그너스")
        charNameArray.add("자히르")
        charNameArray.add("나딘")
        charNameArray.add("현우")
        charNameArray.add("하트")
        charNameArray.add("아이솔")
        charNameArray.add("리 다이린")
        charNameArray.add("유키")
        charNameArray.add("혜진")
        charNameArray.add("쇼우")
        charNameArray.add("키아라")
        charNameArray.add("시셀라")
        charNameArray.add("실비아")
        charNameArray.add("아드리아나")
        charNameArray.add("쇼이치")
        charNameArray.add("엠마")
        charNameArray.add("레녹스")
        charNameArray.add("로지")
        charNameArray.add("루크")

        // total - solo 버튼 리스너
        findViewById<TextView>(R.id.total_btn_solo).setOnClickListener {

            val solo_charcode1 = 1
            val solo_charcode2 = 2
            val solo_charcode3 = 3
            // solo newFrag가 null을 return함.
            val solo_newFrag = supportFragmentManager.findFragmentById(R.id.total_summary) as total_summary_fragment

            solo_newFrag?.changeC1(charImageArray[solo_charcode1-1], charNameArray[solo_charcode1-1], "AVG 1st", "11게임")
            solo_newFrag?.changeC2(charImageArray[solo_charcode2-1], charNameArray[solo_charcode2-1], "AVG 1st", "11게임")
            solo_newFrag?.changeC3(charImageArray[solo_charcode3-1], charNameArray[solo_charcode3-1], "AVG 1st", "11게임")
        }

        // total - duo 버튼 리스너
        findViewById<TextView>(R.id.total_btn_duo).setOnClickListener {

            val charcode1 = 11
            val charcode2 = 12
            val charcode3 = 13
            val duo_newFrag = supportFragmentManager.findFragmentById(R.id.total_summary) as total_summary_fragment

            duo_newFrag.changeC1(charImageArray[charcode1-1], charNameArray[charcode1-1], "AVG 2nd", "22게임")
            duo_newFrag.changeC2(charImageArray[charcode2-1], charNameArray[charcode2-1], "AVG 2nd", "22게임")
            duo_newFrag.changeC3(charImageArray[charcode3-1], charNameArray[charcode3-1], "AVG 2nd", "22게임")
        }

        // total - squad 버튼 리스너
        findViewById<TextView>(R.id.total_btn_squad).setOnClickListener {

            val charcode1 = 17
            val charcode2 = 18
            val charcode3 = 19
            val squad_newFrag = supportFragmentManager.findFragmentById(R.id.total_summary) as total_summary_fragment

            squad_newFrag.changeC1(charImageArray[charcode1-1], charNameArray[charcode1-1], "AVG 3rd", "33게임")
            squad_newFrag.changeC2(charImageArray[charcode2-1], charNameArray[charcode2-1], "AVG 3rd", "33게임")
            squad_newFrag.changeC3(charImageArray[charcode3-1], charNameArray[charcode3-1], "AVG 3rd", "33게임")
        }

        supportFragmentManager.beginTransaction().replace(R.id.total_summary, total_summary_fragment()).commit()

        val Base_URL_BSURL = "https://testbsserver.herokuapp.com"
        val retrofit = Retrofit.Builder().baseUrl(Base_URL_BSURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val client = retrofit.create(BsAPI::class.java)
        val userNumber = intent.getIntExtra("UserNumber", 0).toString()
        findViewById<TextView>(R.id.profile_name).text = intent.getStringExtra("UserNickname")
        if (userNumber != "0") {
            client.getUserStats(userNumber, "1").enqueue(object : Callback<stats> {
                override fun onResponse(call: Call<stats>, response: Response<stats>) {
                    // 비주얼 UI 조작
                    stats.add(response?.body() as stats)
                    uiSomething()
                }

                override fun onFailure(call: Call<stats>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

    }

    // UI 변경 함수 (여기에서 인터페이스 수정)
    fun uiSomething(){
        // 1. 캐릭터 별 데이터를 담은 2차원 배열 = charData
        val charData = calc_mostChar()

        // 2. 랭크 계산
        calc_Rank()
    }

    // most 캐릭터 계산
    private fun calc_mostChar(): Array<Array<characterStats>> {
        val totalCharacter = Array(3) { Array<characterStats>(20) { i -> characterStats(i, 0, 0, 0, 0, 0, 0) } }
        
        var mostChar = 0
        var mostTotalGames = 0
        // 솔로
        for( x in stats[0].userStats[0].characterStats){
            totalCharacter[0][x.characterCode].characterCode = x.characterCode
            totalCharacter[0][x.characterCode].totalGames = x.totalGames
            totalCharacter[0][x.characterCode].maxKillings = x.maxKillings
            totalCharacter[0][x.characterCode].top3 = x.top3
            totalCharacter[0][x.characterCode].top3Rate = x.top3Rate
            totalCharacter[0][x.characterCode].averageRank = x.averageRank
            totalCharacter[0][x.characterCode].wins = x.wins
            if( mostTotalGames < x.totalGames ){
                mostTotalGames = x.totalGames
                mostChar = x.characterCode
            }
        }
        // 듀오
        for( x in stats[0].userStats[1].characterStats){
            totalCharacter[1][x.characterCode].characterCode = x.characterCode
            totalCharacter[1][x.characterCode].totalGames = x.totalGames
            totalCharacter[1][x.characterCode].maxKillings = x.maxKillings
            totalCharacter[1][x.characterCode].top3 = x.top3
            totalCharacter[1][x.characterCode].top3Rate = x.top3Rate
            totalCharacter[1][x.characterCode].averageRank = x.averageRank
            totalCharacter[1][x.characterCode].wins = x.wins
            if( mostTotalGames < x.totalGames ){
                mostTotalGames = x.totalGames
                mostChar = x.characterCode
            }
        }
        // 스쿼드
        for( x in stats[0].userStats[2].characterStats){
            totalCharacter[2][x.characterCode].characterCode = x.characterCode
            totalCharacter[2][x.characterCode].totalGames = x.totalGames
            totalCharacter[2][x.characterCode].maxKillings = x.maxKillings
            totalCharacter[2][x.characterCode].top3 = x.top3
            totalCharacter[2][x.characterCode].top3Rate = x.top3Rate
            totalCharacter[2][x.characterCode].averageRank = x.averageRank
            totalCharacter[2][x.characterCode].wins = x.wins
            if( mostTotalGames < x.totalGames ){
                mostTotalGames = x.totalGames
                mostChar = x.characterCode
            }
        }
        
        // 프로필 캐릭터 사진 변경
        findViewById<ImageView>(R.id.profile_image).setImageResource(charProfileArray[mostChar-1])
        return totalCharacter
    }

    // Rank, 평균 등수, 티어표(Summary) 3가지
    private fun calc_Rank() {
        val soloMMR = stats[0].userStats[0].mmr
        val soloRank = stats[0].userStats[0].rank
        val soloRankSize = stats[0].userStats[0].rankSize

        val duoMMR = stats[0].userStats[1].mmr
        val duoRank = stats[0].userStats[1].rank
        val duoRankSize = stats[0].userStats[1].rankSize

        val squadMMR = stats[0].userStats[2].mmr
        val squadRank = stats[0].userStats[2].rank
        val squadRankSize = stats[0].userStats[2].rankSize

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
        var maxRankSize : Double
        var maxRank = if(soloRank > duoRank){
            if(soloRank > squadRank){
                maxRankSize = soloRankSize.toDouble()
                soloRank.toDouble()
            }else{
                maxRankSize = squadRankSize.toDouble()
                squadRank.toDouble()
            }
        }else{
            if(duoRank > squadRank){
                maxRankSize = duoRankSize.toDouble()
                duoRank.toDouble()
            }else{
                maxRankSize = squadRankSize.toDouble()
                squadRank.toDouble()
            }
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
        if(maxRank == 0.0){
            findViewById<TextView>(R.id.profile_rank).text = "최고 MMR의 배치 정보가 없습니다."
        } else{
            findViewById<TextView>(R.id.profile_rank).text = "상위 ${maxRank}위 (상위 ${Math.round(maxRank / maxRankSize * 100)*10/10.0}%)"
        }

        // 평균 등수
        val soloGrade = stats[0].userStats[0].averageRank
        val duoGrade = stats[0].userStats[1].averageRank
        val squadGrade = stats[0].userStats[2].averageRank
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
        findViewById<TextView>(R.id.profile_averRank).text = String.format("#%.1f", averGrade / count)

        /*findViewById<ImageView>(R.id.profile_text).text = when (maxMmr) {
            in 0..399 -> {
                when(maxMmr){
                    in 0..99 -> "아이언 IV"
                    in 100..199 -> "아이언 III"
                    in 200..299 -> "아이언 II"
                    else ->  "아이언 I"

                }
            }
            in 400..799 -> {
                when(maxMmr){
                    in 400..499 -> "브론즈 IV"
                    in 500..599 -> "브론즈 III"
                    in 600..699 -> "브론즈 II"
                    else ->  "브론즈 I"
                }
            }
            in 800..1299 -> {
                when(maxMmr){
                    in 800..999 -> "실버 IV"
                    in 900..999 -> "실버 III"
                    in 1000..1199 -> "실버 II"
                    else ->  "실버 I"
                }
            }
            in 1300..1699 -> {
                when(maxMmr){
                    in 1300..1399 -> "골드 IV"
                    in 1400..1499 -> "골드 III"
                    in 1500..1599 -> "골드 II"
                    else ->  "골드 I"
                }
            }
            in 1700..2099 -> {
                when(maxMmr){
                    in 1700..1799 -> "플래티넘 IV"
                    in 1800..1899 -> "플래티넘 III"
                    in 1900..1999 -> "플래티넘 II"
                    else ->  "플래티넘 I"
                }
            }
            in 2100..2499 -> {
                when(maxMmr){
                    in 2100..2199 -> "다이아 IV"
                    in 2200..2299 -> "다이아 III"
                    in 2300..2399 -> "다이아 II"
                    else ->  "다이아 I"
                }
            }
            in 2500..2899 -> {
                when(maxMmr){
                    in 2500..2599 -> "데미갓 IV"
                    in 2600..2699 -> "데미갓 III"
                    in 2700..2799 -> "데미갓 II"
                    else ->  "데미갓 I"
                }
            }
            else -> {
                when(maxMmr){
                    in 2900..2999 -> "이터니티 IV"
                    in 3000..3099 -> "이터니티 III"
                    in 3100..3199 -> "이터니티 II"
                    else ->  "이터니티 I"
                }
            }
        }*/

        // Summary - 솔로 MMR 및 Tier 그림
        findViewById<TextView>(R.id.summary_txt_soloMMR).setText("${soloMMR} LP")
        when (soloMMR) {
            in 0..399 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_iron)
            }
            in 400..799 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_bronze)
            }
            in 800..1299 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_silver)
            }
            in 1300..1699 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_gold)
            }
            in 1700..2099 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_platinum)
            }
            in 2100..2499 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_diamond)
            }
            in 2500..2899 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_demigod)
            }
            else -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_eternity)
            }
        }

        findViewById<TextView>(R.id.summary_txt_duoMMR).setText("${duoMMR} LP")
        when (duoMMR) {
            in 0..399 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_iron)
            }
            in 400..799 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_bronze)
            }
            in 800..1299 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_silver)
            }
            in 1300..1699 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_gold)
            }
            in 1700..2099 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_platinum)
            }
            in 2100..2499 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_diamond)
            }
            in 2500..2899 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_demigod)
            }
            else -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_eternity)
            }
        }

        findViewById<TextView>(R.id.summary_txt_squadMMR).setText("${squadMMR} LP")
        when (squadMMR) {
            in 0..399 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_iron)
            }
            in 400..799 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_bronze)
            }
            in 800..1299 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_silver)
            }
            in 1300..1699 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_gold)
            }
            in 1700..2099 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_platinum)
            }
            in 2100..2499 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_diamond)
            }
            in 2500..2899 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_demigod)
            }
            else -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_eternity)
            }
        }


    }
}