package com.sg.sgrr

class MostCharacter(characterCode: Int, totalGames: Int, averageRank: Double) {
    private var mCharacterCode:Int = characterCode
    private var mTotalGames:Int = totalGames
    private var mAverageRank:Double = averageRank

    fun updateMost(totalGames: Int, averageRank: Double){
        mTotalGames += totalGames
        if(mAverageRank < averageRank)
            mAverageRank = averageRank
    }

    fun getCharacterCode(): Int {
        return mCharacterCode
    }

    fun getAverageRank():Double{
        return mAverageRank
    }
}