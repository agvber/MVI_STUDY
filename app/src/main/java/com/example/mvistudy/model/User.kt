package com.example.mvistudy.model

data class User(
    val name: String,
    val gender: Gender,
    val age: Int,
    val introduce: String,
    val profileImageUrl: String?
)
