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

    private var currentPage = 1
    private val perPage = 10
    private var totalPages = 1

    private var isLoadingMore = false

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                currentPage = 1

                val response = repository.getUsers(
                    page = currentPage,
                    perPage = perPage
                )

                totalPages = response.total_pages
                _users.value = response.data

            } catch (e: Exception) {
                e.printStackTrace()

            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadNextPage() {
        if (isLoadingMore) return
        if (currentPage >= totalPages) return

        viewModelScope.launch {
            try {
                isLoadingMore = true

                val nextPage = currentPage + 1

                val response = repository.getUsers(
                    page = nextPage,
                    perPage = perPage
                )

                currentPage = nextPage
                totalPages = response.total_pages

                _users.value = _users.value + response.data

            } catch (e: Exception) {
                e.printStackTrace()

            } finally {
                isLoadingMore = false
            }
        }
    }
}