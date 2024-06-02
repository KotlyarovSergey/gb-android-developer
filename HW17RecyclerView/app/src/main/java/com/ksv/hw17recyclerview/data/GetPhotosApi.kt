package com.ksv.hw17recyclerview.data

import com.ksv.hw17recyclerview.entity.DataPhotos
import com.ksv.hw17recyclerview.entity.Photos
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos"
private const val BASE_URL = "https://api.nasa.gov"


object RetrofitInstance{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val getPhotosApiResponse: GetPhotosApi = retrofit.create(
        GetPhotosApi::class.java
    )
}


interface GetPhotosApi{
    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getPhotos(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("camera") camera: String = CAMERA
    ): Photos

    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getPhotosResponse(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("camera") camera: String = CAMERA
    ): Response<Photos>

    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getDataPhotosResponse(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("camera") camera: String = CAMERA
    ): Response<DataPhotos>



    companion object{
        private const val API_KEY = "rf5ijwgbwyTvVqJJOcXOWxBT4ZtPlDhZ7M5NZp8m"
        private const val CAMERA = "FHAZ"
//        private const val CAMERA = "NAVCAM"
    }
}

