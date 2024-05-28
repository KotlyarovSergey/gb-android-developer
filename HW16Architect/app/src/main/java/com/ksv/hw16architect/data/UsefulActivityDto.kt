package com.ksv.hw16architect.data

import com.ksv.hw16architect.entity.UsefulActivity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UsefulActivityDto(
    @Json(name="activity") override val activity: String,
    @Json(name="availability") override val availability: Float,
    @Json(name="type") override val type: String,
    @Json(name="participants") override val participants: Int,
    @Json(name="price") override val price: Float,
    @Json(name="accessibility") override val accessibility: String,
    @Json(name="duration") override val duration: String,
    @Json(name="kidFriendly") override val kidFriendly: Boolean,
    @Json(name="link") override val link: String,
    @Json(name="key") override val key: Int
) : UsefulActivity