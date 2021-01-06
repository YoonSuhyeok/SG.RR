package com.sg.sgrr.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class stats(
        @SerializedName("code") @Expose val code: Int,
        @SerializedName("message") @Expose val message: String,
        @SerializedName("userStats") @Expose val user: List<userStats>)

data class userStats(
        @SerializedName("seasonId") @Expose val seasonId: Int,
        @SerializedName("userNum") @Expose val userNum: Int,
        @SerializedName("matchingMode") @Expose val matchingMode: Int,
        @SerializedName("matchingTeamMode") @Expose val matchingTeamMode: Int,
        @SerializedName("mmr") @Expose val mmr: Int,
        @SerializedName("nickname") @Expose val nickname: String,
        @SerializedName("rank") @Expose val rank: Int,
        @SerializedName("rankSize") @Expose val rankSize: Int,
        @SerializedName("totalGames") @Expose val totalGames: Int,
        @SerializedName("totalWins") @Expose val totalWins: Int,
        @SerializedName("rankPercent") @Expose val rankPercent: Int,
        @SerializedName("averageRank") @Expose val averageRank: Int,
        @SerializedName("averageKills") @Expose val averageKills: Int,
        @SerializedName("averageAssistants") @Expose val averageAssistants: Int,
        @SerializedName("averageHunts") @Expose val averageHunts: Int,
        @SerializedName("top1") @Expose val top1: Int,
        @SerializedName("top2") @Expose val top2: Int,
        @SerializedName("top3") @Expose val top3: Int,
        @SerializedName("top5") @Expose val top5: Int,
        @SerializedName("top7") @Expose val top7: Int,
        @SerializedName("characterStats") @Expose val characterStats: List<characterStats>)

data class characterStats(
        @SerializedName("characterCode") @Expose val characterCode: Int,
        @SerializedName("totalGames") @Expose val totalGames: Int,
        @SerializedName("maxKillings") @Expose val maxKillings: Int,
        @SerializedName("top3") @Expose val top3: Int,
        @SerializedName("top3Rate") @Expose val top3Rate: Int,
        @SerializedName("averageRank") @Expose val averageRank: Int,
        @SerializedName("wins") @Expose val wins: Int)