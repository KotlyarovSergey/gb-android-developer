package com.ksv.hw17recyclerview.data

import com.ksv.hw17recyclerview.entity.Photos
import com.ksv.hw17recyclerview.entity.PhotosItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosDto(
    @Json(name="photos") override val photos: List<PhotosItem>
) : Photos