package com.ksv.lesson14

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://api.thecatapi.com"

object RetrofitInstance{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchImageApi: SearchImageApi = retrofit.create(
        SearchImageApi::class.java
    )
    val searchImageApiCorutine: SearchImageApiCorutine = retrofit.create(
        SearchImageApiCorutine::class.java
    )
    val searchImageApiResponse: SearchImageApiResonse = retrofit.create(
        SearchImageApiResonse::class.java
    )
}
interface SearchImageApi{
    @GET("v1/images/search")
    fun getCatImageList(): Call<List<CatMoshi>>
}


interface SearchImageApiCorutine{
    @GET("v1/images/search")
    suspend fun getCatImageList(): List<CatMoshi>
}

interface SearchImageApiResonse{
    @GET("v1/images/search")
    suspend fun getCatImageList(): Response<List<CatMoshi>>
}

//
//interface SearchImageApi{
//    @Headers(
//        "Accept: application/json",
//        "Content-type: application/json"
//    )
////    @GET("/v1/images/search?limit=1")
//    @GET("/v1/images/search")
//    fun getCatImage(@Query("limit") limit: Int = 1): Call<List<CatGson>>
//}

