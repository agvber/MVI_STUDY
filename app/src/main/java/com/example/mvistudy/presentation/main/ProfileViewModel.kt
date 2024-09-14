package com.example.mvistudy.presentation.main

import androidx.lifecycle.ViewModel
import com.example.mvistudy.data.UserRepository
import com.example.mvistudy.model.ProfileSidEffect
import com.example.mvistudy.model.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.firstOrNull
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(), ContainerHost<ProfileState, ProfileSidEffect> {

    override val container: Container<ProfileState, ProfileSidEffect> =
        container(
            initialState = ProfileState(),
            buildSettings = {
                exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                    intent {
                        postSideEffect(ProfileSidEffect.ServerError(throwable))
                    }
                }
            }
        )

    init {
        intent {
            val user = userRepository.user.firstOrNull()
                .also {
                    if (it == null) {
                        postSideEffect(ProfileSidEffect.StorageError)
                    }
                }
                ?: return@intent


            reduce {
                state.copy(
                    username = user.name,
                    gender = user.gender,
                    profileImageUrl = user.profileImageUrl,
                    introduce = user.introduce
                )
            }
        }
    }


    fun changeUsername(username: String) = intent {
        reduce { state.copy(username = username) }
    }

    fun changeGender(username: String) = intent {
        reduce { state.copy(username = username) }
    }

    fun changeIntroduce(introduce: String) = intent {
        reduce {
            if (getRandomNumber() == 0) {
                throw IllegalStateException()
            }
            state.copy(introduce = introduce)
        }
    }

    private fun getRandomNumber(): Int {
        return Random.nextInt(0, 5)
    }

}