package com.mkshmnv.djinni.ui.profile.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkshmnv.djinni.App
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.Resource
import com.mkshmnv.djinni.Toast
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    // For logger
    private val tag = this::class.simpleName!!
    private val context = App.instance

    // Get repository
    private val userRepository = UserRepository()

    init {
        Logger.logcat("UserViewModel init - $this", tag)
    }

    // LiveData
    private val _authorizedUser = MutableLiveData<User>()
    val authorizedUser: LiveData<User> = _authorizedUser

    fun signUpUser(email: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.registrationUser(email, password)
            when (result) {
                is Resource.Success -> {
                    signInUser(email = email, password = password)
                    Toast.showWithLogger(
                        context.getString(R.string.auth_welcome_to_djinni),
                        "$tag signUpUser"
                    )
                }

                is Resource.Error -> Toast.showWithLogger(result.message, "$tag signUpUser")
                else -> Toast.showWithLogger(context.getString(R.string.error), "$tag signUpUser")
            }
        }
    }

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.loginUser(email, password)
            _authorizedUser.postValue(user)
        }
    }

    fun signInWithGoogle() {
        // TODO: impl signInWithGoogle
        Toast.showWithLogger(text = "Google sign in not implemented yet", tag = tag)
    }

//    fun signOut() {
//        userRepository.signOut()
//        _authorizedUser.postValue(null)
//    }
}