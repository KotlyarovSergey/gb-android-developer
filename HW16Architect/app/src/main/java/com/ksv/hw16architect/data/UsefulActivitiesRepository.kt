package com.ksv.hw16architect.data

import android.util.Log
import com.ksv.hw16architect.entity.UsefulActivity

class UsefulActivitiesRepository {
    private var usefulActivity = UsefulActivityDto(
        activity = "Go on a long drive with no music",
        availability = 0.2f,
        type = "relaxation",
        participants = 1,
        price = 0.1f,
        accessibility = "Minor challenges",
        duration = "hours",
        kidFriendly = true,
        link = "",
        key = 4290333
    )

    suspend fun getUsefulActivity(): UsefulActivity {

        try {
            val response = RetrofitInstance.getUsefulActivityResponse.getUsefulActivityData()
            if (response.isSuccessful)
                return response.body()!!
        }catch (ex: Exception){
            Log.d("ksvlog", "${ex.message}")
        }

        return usefulActivity
    }
}