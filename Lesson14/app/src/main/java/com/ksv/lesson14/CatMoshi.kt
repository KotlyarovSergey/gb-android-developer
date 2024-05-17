package com.ksv.lesson14

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatMoshi (
    @Json(name="id") val id: String,
    @Json(name="url") val url: String
)