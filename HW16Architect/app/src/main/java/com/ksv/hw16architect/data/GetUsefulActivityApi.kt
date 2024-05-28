package com.ksv.hw16architect.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.boredapi.com"

object RetrofitInstance{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val getUsefulActivityResponse: GetUsefulActivityResponse = retrofit.create(
        GetUsefulActivityResponse::class.java
    )
}
interface GetUsefulActivityResponse{
    @GET("api/activity")
    suspend fun getUsefulActivityData(): Response<UsefulActivityDto>
}
