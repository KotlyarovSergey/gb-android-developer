package com.ksv.hw17recyclerview.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataPhotos(
    @Json(name="photos") val photos: List<PhotoItem>
)

@JsonClass(generateAdapter = true)
data class PhotoItem(
    @Json(name="img_src") val url: String,
    @Json(name="sol") val sol: Int,
    @Json(name="id") val id: Int,
    @Json(name="earth_date") val date: String,
    @Json(name="rover") val rover: RoverName,
    @Json(name="camera") val camera: CameraName
)

@JsonClass(generateAdapter = true)
data class RoverName(
    @Json(name="name") val name: String
)
@JsonClass(generateAdapter = true)
data class CameraName(
    @Json(name="name") val name: String
)