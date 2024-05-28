package com.ksv.hw16architect.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksv.hw16architect.domain.GetUsefulActivityUseCase
import com.ksv.hw16architect.entity.UsefulActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getUsefulActivityUseCase: GetUsefulActivityUseCase
): ViewModel() {

    private var _usefulActivity = MutableStateFlow<UsefulActivity?>(null)
    val usefulActivity = _usefulActivity.asStateFlow()

    fun reloadUsefulActivity(){
        viewModelScope.launch {
            _usefulActivity.value = getUsefulActivityUseCase.execute()
        }
    }
}