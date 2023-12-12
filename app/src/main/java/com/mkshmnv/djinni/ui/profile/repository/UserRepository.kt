package com.mkshmnv.djinni.ui.profile.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.mkshmnv.djinni.Resource
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference.child("users")

    suspend fun createUser(email: String, password: String): Resource<User> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(uid = authResult.user?.uid ?: "null", email = email)
            database.child(user.uid).setValue(user)
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Registration failed")
        }
    }

    suspend fun signInUser(email: String, password: String): Resource<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val user = User(uid = authResult.user?.uid ?: "null", email = email)
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Login failed")
        }
    }

    private fun getCurrentUser(): FirebaseUser {
        return auth.currentUser!! // TODO: remove !! operator
    }

    suspend fun getCurrentUserData(): Resource<User> {
        val user = getCurrentUser()
        return try {
            val snapshot = database.child(user.uid).get().await()
            val userData = snapshot.getValue(User::class.java)
            Resource.Success(userData!!)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error getting user data")
        }
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun updateUserProfile(user: User): Resource<User> {
        return try {
            val userID = getCurrentUser().uid
            database.child(userID).setValue(user).await()
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error getting user data")
        }
    }
}