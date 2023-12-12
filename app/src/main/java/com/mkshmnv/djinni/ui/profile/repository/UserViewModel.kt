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

    fun signUpUser(email: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.createUser(email, password)
            when (result) {
                is Resource.Success -> {
                    signInUser(email = email, password = password)
                    Toast.showWithLogger(
                        context = context,
                        text = context.getString(R.string.welcome_to_djinni),
                        tag = "signUp $tag"
                    )
                }

                is Resource.Error -> {
                    Toast.showWithLogger(
                        context = context,
                        text = result.message,
                        tag = "signUp $tag"
                    )
                }

                else -> {
                    Toast.showWithLogger(
                        context = context,
                        text = context.getString(R.string.error),
                        tag = "signIn $tag"
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
                        context = context,
                        text = context.getString(R.string.welcome_to_djinni),
                        tag = "signUp $tag"
                    )
                }

                is Resource.Error -> {
                    Toast.showWithLogger(
                        context = context,
                        text = result.message,
                        tag = "signUp $tag"
                    )
                }

                else -> {
                    Toast.showWithLogger(
                        context = context,
                        text = context.getString(R.string.error),
                        tag = "signIn $tag"
                    )
                }
            }
        }
    }

    fun signInWithGoogle() {
        Toast.showWithLogger(
            context = context,
            text = "Google sign in not implemented yet",
            tag = tag
        )
    }

    fun getUserData() {
        viewModelScope.launch {
            val result = userRepository.getCurrentUserData()
            when (result) {
                is Resource.Success -> {
                    _authorizedUser.postValue(result.data)
                    Toast.showWithLogger(
                        context = context,
                        text = context.getString(R.string.welcome_to_djinni),
                        tag = "signUp $tag"
                    )
                }

                is Resource.Error -> {
                    Toast.showWithLogger(
                        context = context,
                        text = result.message,
                        tag = "signUp $tag"
                    )
                }

                else -> {
                    Toast.showWithLogger(
                        context = context,
                        text = context.getString(R.string.error),
                        tag = "signIn $tag"
                    )
                }
            }
        }
    }

    fun signOut() {
        userRepository.signOut()
        _authorizedUser.postValue(null)
    }

    fun updateUserProfile(uiUser: User) {
        val updatedUser = verifyUser(uiUser)
        viewModelScope.launch {
            val result = userRepository.updateUserProfile(updatedUser)
            when (result) {
                is Resource.Success -> {
                    _authorizedUser.postValue(result.data)
                    Toast.showWithLogger(
                        context = context,
                        text = context.getString(R.string.welcome_to_djinni),
                        tag = "signUp $tag"
                    )
                }

                is Resource.Error -> {
                    Toast.showWithLogger(
                        context = context,
                        text = result.message,
                        tag = "signUp $tag"
                    )
                }

                else -> {
                    Toast.showWithLogger(
                        context = context,
                        text = context.getString(R.string.error),
                        tag = "signIn $tag"
                    )
                }
            }
        }
        _authorizedUser.postValue(updatedUser)
    }

    private fun verifyUser(uiUser: User): User {
        val uiUserMap = uiUser::class.members
            .filterIsInstance<KProperty1<User, *>>()
            .associateBy({ it.name }, { it.get(uiUser) })

        val authorizedUser = authorizedUser.value!!
        val currentUserMap = authorizedUser::class.members
            .filterIsInstance<KProperty1<User, *>>()
            .associateBy({ it.name }, { it.get(authorizedUser) }).toMutableMap()

        uiUserMap.forEach { (property, newValue) ->
            if (newValue != "" && newValue != currentUserMap[property]) {
                currentUserMap[property] = newValue
            }
        }

        val userClassType = User::class.java
        val tempUser = userClassType.getDeclaredConstructor().newInstance()
        currentUserMap.forEach { (property, value) ->
            val property = userClassType.getDeclaredField(property)
            property.isAccessible = true
            property.set(tempUser, value)
        }

        return tempUser
    }
}