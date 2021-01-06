package com.sg.sgrr.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class games(
        @SerializedName("code") @Expose val code: Int,
        @SerializedName("message") @Expose val message: String,
        @SerializedName("userStats") @Expose val user: List<userGames>
)
data class userGames(
        @SerializedName("userNum") @Expose val userNum: Int,
        @SerializedName("nickname") @Expose val nickname: String,
        @SerializedName("gameId") @Expose val gameId: Int,
        @SerializedName("seasonId") @Expose val seasonId: Int,
        @SerializedName("matchingMode") @Expose val matchingMode: String,
        @SerializedName("matchingTeamMode") @Expose val matchingTeamMode: Int,
        @SerializedName("characterNum") @Expose val characterNum: Int,
        @SerializedName("characterLevel") @Expose val characterLevel: String,
        @SerializedName("gameRank") @Expose val gameRank: Int,
        @SerializedName("playerKill") @Expose val playerKill: Int,
        @SerializedName("playerAssistant") @Expose val playerAssistant: String,
        @SerializedName("monsterKill") @Expose val monsterKill: Int,
        @SerializedName("bestWeapon") @Expose val bestWeapon: Int,
        @SerializedName("bestWeaponLevel") @Expose val bestWeaponLevel: String,
        @SerializedName("masteryLevel") @Expose val masteryLevel: Int )