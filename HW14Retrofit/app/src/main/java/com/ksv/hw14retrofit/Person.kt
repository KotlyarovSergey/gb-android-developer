package com.ksv.hw14retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Person(
    @Json(name="results") val results: List<Result>,
    @Json(name="info") val info: Info
)
@JsonClass(generateAdapter = true)
data class Info(
    @Json(name="seed") val seed: String,
    @Json(name="results") val results: String,
    @Json(name="page") val page: String
)
@JsonClass(generateAdapter = true)
data class Result(
    @Json(name="gender") val gender: String,
    @Json(name="name") val name: Name,
    @Json(name="location") val location: Location,
    @Json(name="email") val email: String,
    @Json(name="picture") val pictureUrl: PictureUrl

)
@JsonClass(generateAdapter = true)
data class Name(
    @Json(name="title") val title: String,
    @Json(name="first") val first: String,
    @Json(name="last") val last: String
)
@JsonClass(generateAdapter = true)
data class PictureUrl(
    @Json(name="large") val large: String,
    @Json(name="medium") val medium: String,
    @Json(name="thumbnail") val thumbnail: String
)
@JsonClass(generateAdapter = true)
data class Location(
    @Json(name="street") val street: Street,
    @Json(name="city") val city: String,
    @Json(name="state") val state: String,
    @Json(name="country") val country: String
)
@JsonClass(generateAdapter = true)
data class Street(
    @Json(name="number") val number: String,
    @Json(name="name") val name: String
)


/*
    "name": {
                "title": "Mr",
                "first": "Christopher",
                "last": "Clark"
            },
*/