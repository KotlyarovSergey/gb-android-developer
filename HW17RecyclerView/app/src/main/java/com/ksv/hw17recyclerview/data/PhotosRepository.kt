package com.ksv.hw17recyclerview.data

import android.util.Log
import com.ksv.hw17recyclerview.entity.PhotoItem
import com.ksv.hw17recyclerview.entity.Photos

class PhotosRepository {


    // не работает!!
    // выпадает исключение Unable to create converter for class com.ksv.hw17recyclerview.data.Photos
    suspend fun getPhotos(sol: Int = 1000): Photos? {
        try {
            val response = RetrofitInstance.getPhotosApiResponse.getPhotosResponse(sol)
            if (response.isSuccessful)
                return response.body()!!
            else
                return null
        } catch (ex: Exception) {
            Log.d("ksvlog", "${ex.message}")
        }

        return null
    }

    suspend fun getPhotosList(sol: Int = 1000): List<PhotoItem> {
        try {
            val response = RetrofitInstance.getPhotosApiResponse.getDataPhotosResponse(sol)
            return if (response.isSuccessful)
                response.body()?.photos ?: emptyList()
            else
                emptyList()
        } catch (ex: Exception) {
            Log.d("ksvlog", "${ex.message}")
        }

        return emptyList()
    }
}