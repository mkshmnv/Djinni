package com.mkshmnv.djinni.ui.profile.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.mkshmnv.djinni.Resource
import com.mkshmnv.djinni.Toast
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference.child("users")

    suspend fun createUser(email: String, password: String): Resource<User> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user =
                User(uid = authResult.user?.uid ?: "error - wrong uid createUser", email = email)
            database.child(user.uid).setValue(user)
            Resource.Success(user)
        } catch (e: Exception) {
            Toast.showWithLogger(text = e.message ?: "Registration failed", tag = "createUser")
            Resource.Error(e.message ?: "Registration failed")
        }
    }

    suspend fun signInUser(email: String, password: String): Resource<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val user =
                User(uid = authResult.user?.uid ?: "error - wrong uid signInUser", email = email)
            Resource.Success(user)
        } catch (e: Exception) {
            Toast.showWithLogger(text = e.message ?: "Login failed", tag = "createUser")
            Resource.Error(e.message ?: "Login failed")
        }
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

    private fun getCurrentUser(): FirebaseUser {
        return auth.currentUser!! // TODO: remove !! operator
    }

    fun signOut() {
        auth.signOut()
    }
}