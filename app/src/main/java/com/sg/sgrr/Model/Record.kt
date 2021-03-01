package com.sg.sgrr.Model

class Record(
        var userName: String,
        var rank: String,
        var rankNumber:String,
        var rankPercent: String,
        var mostImage1:Int,
        var mostImage2:Int,
        var mostImage3:Int,
        var mostPercent1: Int,
        val mostPercent2: Int,
        val mostPercent3: Int,
        val userId: Int
) {
    var id:Int? = null
}