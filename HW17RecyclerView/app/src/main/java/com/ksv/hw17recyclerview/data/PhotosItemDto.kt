package com.ksv.hw17recyclerview.data

import com.ksv.hw17recyclerview.entity.PhotosItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PhotosItemDto(
    @Json(name="earth_date") override val earthDate: String,
    @Json(name="id") override val id: Int,
    @Json(name="img_src") override val imgSrc: String,
    @Json(name="sol") override val sol: Int
) : PhotosItem