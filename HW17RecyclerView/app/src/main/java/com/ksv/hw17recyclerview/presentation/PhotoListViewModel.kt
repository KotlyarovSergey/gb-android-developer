package com.ksv.hw17recyclerview.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksv.hw17recyclerview.data.PhotosRepository
import com.ksv.hw17recyclerview.entity.PhotoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoListViewModel : ViewModel() {
    private val repository = PhotosRepository()
    private val _photos = MutableStateFlow<List<PhotoItem>>(emptyList())
    val photos = _photos.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _photos.value = repository.getPhotosList(3344)

            } catch (exception: Exception) {
                Log.d("ksvlog", "repository error: ${exception.message}")
            }
            _isLoading.value = false
        }
    }
}