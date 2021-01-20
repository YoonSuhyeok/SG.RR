package com.sg.sgrr.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class stats(
        @SerializedName("code") @Expose val code: Int,
        @SerializedName("message") @Expose val message: String,
        @SerializedName("userStats") @Expose val userStats: List<userStats>)

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
        @SerializedName("rankPercent") @Expose val rankPercent: Double,
        @SerializedName("averageRank") @Expose val averageRank: Double,
        @SerializedName("averageKills") @Expose val averageKills: Double,
        @SerializedName("averageAssistants") @Expose val averageAssistants: Double,
        @SerializedName("averageHunts") @Expose val averageHunts: Double,
        @SerializedName("top1") @Expose val top1: Double,
        @SerializedName("top2") @Expose val top2: Double,
        @SerializedName("top3") @Expose val top3: Double,
        @SerializedName("top5") @Expose val top5: Double,
        @SerializedName("top7") @Expose val top7: Double,
        @SerializedName("characterStats") @Expose val characterStats: List<characterStats>)

data class characterStats(
        @SerializedName("characterCode") @Expose var characterCode: Int,
        @SerializedName("totalGames") @Expose var totalGames: Int,
        @SerializedName("maxKillings") @Expose var maxKillings: Int,
        @SerializedName("top3") @Expose var top3: Int,
        @SerializedName("top3Rate") @Expose var top3Rate: Int,
        @SerializedName("averageRank") @Expose var averageRank: Int,
        @SerializedName("wins") @Expose var wins: Int)