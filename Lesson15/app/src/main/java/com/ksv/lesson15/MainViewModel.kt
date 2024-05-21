package com.ksv.lesson15

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val userDao: UserDao) : ViewModel() {
    val allUsers: StateFlow<List<User>> = this.userDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun onAddButton() {
        val size = allUsers.value.size
        viewModelScope.launch {
            userDao.insert(
                NewUser(
                    firstName = "Name $size",
                    lastName = "LastName $size",
                    age = size
                )
            )
        }
    }

    fun onUpdateButton() {
        viewModelScope.launch {
            allUsers.value.lastOrNull()?.let {
                val user = it.copy(
                    lastName = "Petrov"
                )
                userDao.update(user)
            }
        }
    }

    fun onDeleteButton() {
        viewModelScope.launch {
            allUsers.value.lastOrNull()?.let { userDao.delete(it) }
        }
    }


    fun filterByName(name: String) : List<User> {
//        var lst: List<User>
//        viewModelScope.launch {
//            val list = userDao.selectByName(name)
//            lst = userDao.selectByName(name)
//            return@launch
//        }
        return emptyList()
    }


    fun updateNameById(id: Int, newName: String){
        viewModelScope.launch {
            userDao.updateById(id, newName)
        }
    }

}