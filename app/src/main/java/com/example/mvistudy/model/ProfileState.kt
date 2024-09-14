package com.example.mvistudy.model

data class ProfileState(
    val username: String = "",
    val gender: Gender = Gender.MALE,
    val introduce: String = "",
    val profileImageUrl: String? = null,
    val playList: List<String> = emptyList()
)