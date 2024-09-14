package com.example.mvistudy.data

import com.example.mvistudy.model.Gender
import com.example.mvistudy.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class UserRepository @Inject constructor() {

    private val _user: MutableStateFlow<User> = MutableStateFlow(
        User(
            name = "Android",
            gender = Gender.MALE,
            age = 100,
            introduce = "Hello windows",
            profileImageUrl = null
        )
    )
    val user: Flow<User> = _user.asStateFlow()
}