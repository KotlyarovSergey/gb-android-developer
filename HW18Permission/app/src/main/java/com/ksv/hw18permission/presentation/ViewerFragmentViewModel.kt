package com.ksv.hw18permission.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksv.hw18permission.data.PhotoItemDao
import com.ksv.hw18permission.entity.PhotoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewerFragmentViewModel(private val photoItemDao: PhotoItemDao) : ViewModel() {

    private val _photos = MutableStateFlow<List<PhotoItem>>(emptyList())
    val photos = _photos.asStateFlow()

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            _photos.value = photoItemDao.getAll()
            Log.d("ksvlog", "count: ${photos.value.size}")
        }
    }
}