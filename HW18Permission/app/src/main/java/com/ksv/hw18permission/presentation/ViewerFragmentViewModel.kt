package com.ksv.hw18permission.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksv.hw18permission.data.PhotoItemDao
import com.ksv.hw18permission.entity.PhotoItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ViewerFragmentViewModel(private val photoItemDao: PhotoItemDao) : ViewModel() {
    val photos: StateFlow<List<PhotoItem>> = this.photoItemDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

}