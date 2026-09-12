package com.example.suitmediatest.ui.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediatest.data.api.ApiConfig
import com.example.suitmediatest.data.model.User
import com.example.suitmediatest.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository(ApiConfig.apiService)
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                val response = repository.getUsers(
                    page = 1,
                    perPage = 10
                )

                _users.value = response.data

            } catch (e: Exception) {
                e.printStackTrace()

            } finally {
                _isLoading.value = false
            }
        }
    }
}