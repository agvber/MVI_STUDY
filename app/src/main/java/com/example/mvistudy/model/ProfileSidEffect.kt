package com.example.mvistudy.model

sealed interface ProfileSidEffect {

    data object StorageError : ProfileSidEffect

    data class ServerError(val t: Throwable?) : ProfileSidEffect

    data object Init : ProfileSidEffect
}