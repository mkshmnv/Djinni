package com.mkshmnv.djinni.ui.profile.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkshmnv.djinni.App
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.Resource
import com.mkshmnv.djinni.Toast
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty1

class UserViewModel : ViewModel() {
    // For logger
    private val tag = this::class.simpleName!!
    private val context = App.instance

    // Get repository
    private val userRepository = UserRepository()

    // LiveData
    private val _authorizedUser = MutableLiveData<User?>()
    val authorizedUser: MutableLiveData<User?> = _authorizedUser

    private val _profileStatus = MutableLiveData<String>()
    fun setProfileStatus(status: String) {
        _profileStatus.value = status
    }
    fun signUpUser(email: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.createUser(email, password)
            when (result) {
                is Resource.Success -> {
                    signInUser(email = email, password = password)
                    Toast.showWithLogger(
                        text = context.getString(R.string.auth_welcome_to_djinni),
                        tag = "signUpUser $tag"
                    )
                }

                is Resource.Error -> {
                    Toast.showWithLogger(text = result.message, tag = "signUpUser $tag")
                }

                else -> {
                    Toast.showWithLogger(
                        text = context.getString(R.string.error),
                        tag = "signUpUser $tag"
                    )
                }
            }
        }
    }

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.signInUser(email, password)
            when (result) {
                is Resource.Success -> {
                    getUserData()
                    Toast.showWithLogger(
                        text = context.getString(R.string.auth_welcome_to_djinni),
                        tag = "signInUser $tag"
                    )
                }

                is Resource.Error -> {
                    Toast.showWithLogger(text = result.message, tag = "signInUser $tag")
                }

                else -> {
                    Toast.showWithLogger(
                        text = context.getString(R.string.error),
                        tag = "signInUser $tag"
                    )
                }
            }
        }
    }

    fun signInWithGoogle() {
        // TODO: impl signInWithGoogle
        Toast.showWithLogger(text = "Google sign in not implemented yet", tag = tag)
    }

    fun getUserData() {
        viewModelScope.launch {
            val result = userRepository.getCurrentUserData()
            when (result) {
                is Resource.Success -> {
                    _authorizedUser.postValue(result.data)
                    Toast.showWithLogger(
                        text = "_authorizedUser - ${_authorizedUser.value.toString()}",
                        tag = "getUserData $tag"
                    )
                }

                is Resource.Error -> {
                    Toast.showWithLogger(text = result.message, tag = "getUserData $tag")
                }

                else -> {
                    Toast.showWithLogger(
                        text = context.getString(R.string.error),
                        tag = "getUserData $tag"
                    )
                }
            }
        }
    }

    fun signOut() {
        userRepository.signOut()
        _authorizedUser.postValue(null)
    }
}