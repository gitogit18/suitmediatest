package com.example.suitmediatest.data.repository

import com.example.suitmediatest.data.api.UserApiService

class UserRepository(
    private val apiService: UserApiService
) {

    suspend fun getUsers(
        page: Int,
        perPage: Int
    ) = apiService.getUsers(
        page = page,
        perPage = perPage
    )
}