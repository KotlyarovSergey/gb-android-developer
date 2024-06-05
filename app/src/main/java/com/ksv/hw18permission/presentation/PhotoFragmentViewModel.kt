package com.ksv.hw18permission.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhotoFragmentViewModel: ViewModel() {
    private var _isPermissionGranted = MutableStateFlow(false)
    val isPermissionGranted get() = _isPermissionGranted.asStateFlow()

    fun setPermissionState(isGranted: Boolean) {
        _isPermissionGranted.value = isGranted
    }
}