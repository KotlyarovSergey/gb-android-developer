package com.ksv.hw14retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me"
//private const val BASE_URL = "https://api.thecatapi.com"

object RetrofitInstance{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val getPersonApi: GetPersonApi = retrofit.create(
        GetPersonApi::class.java
    )
    val getPersonApiResponse: GetPersonApiResponse = retrofit.create(
        GetPersonApiResponse::class.java
    )


    val searchImageApiCorutine: SearchImageApiCorutine = retrofit.create(
        SearchImageApiCorutine::class.java
    )
}
interface GetPersonApi{
    @GET("api")
    fun getPersonData(): Call<Person>
}
interface GetPersonApiResponse{
    @GET("api")
    suspend fun getPersonData(): Response<Person>
}

//interface SearchImageApi{
//    @GET("v1/images/search")
//    fun getCatImageList(): Call<List<CatMoshi>>
//}

interface SearchImageApiCorutine{
    @GET("v1/images/search")
    suspend fun getCatImageList(): List<Person>
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

