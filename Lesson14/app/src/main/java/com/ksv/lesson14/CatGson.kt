package com.ksv.lesson14
import com.google.gson.annotations.SerializedName
data class CatGson (
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)