package com.ksv.hw17recyclerview.data

import android.util.Log
import com.ksv.hw17recyclerview.entity.DataPhotos
import com.ksv.hw17recyclerview.entity.PhotoItem
import com.ksv.hw17recyclerview.entity.Photos

class PhotosRepository {

//    suspend fun getPhotos(soul: Int = 1000): Photos? {
    suspend fun getPhotos(soul: Int = 1000): DataPhotos? {
        try {
//            val response = RetrofitInstance.getPhotosApiResponse.getPhotosResponse(soul)
            val response = RetrofitInstance.getPhotosApiResponse.getDataPhotosResponse(soul)
            if (response.isSuccessful)
                return response.body()!!
            else
                return null
        } catch (ex: Exception) {
            Log.d("ksvlog", "${ex.message}")
        }

        return null
    }

    suspend fun getPhotosList(soul: Int = 1000): List<PhotoItem> {
        try {
            val response = RetrofitInstance.getPhotosApiResponse.getDataPhotosResponse(soul)
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