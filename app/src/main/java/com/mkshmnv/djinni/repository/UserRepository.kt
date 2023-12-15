package com.mkshmnv.djinni.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mkshmnv.djinni.model.User
import kotlinx.coroutines.tasks.await

class UserRepository {
    // Firebase
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference.child("users")

    suspend fun registrationUser(email: String, password: String): Resource<User> {
        return try {
            val auth = auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(uid = auth.user?.uid.toString(), email = email)
            database.child(user.uid).setValue(user)
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Registration failed")
        }
    }

    suspend fun loginUser(email: String, password: String): Resource<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val userID = authResult.user?.uid.toString()
            try {
                val snapshot = database.child(userID).get().await()
                val userData = snapshot.getValue(User::class.java)
                Resource.Success(userData!!)
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Error getting user data for $userID")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "SignInWithEmailAndPassword failed")
        }
    }

    suspend fun updateUserToDatabase(user: User): Resource<User> {
        return try {
            database.child(user.uid).setValue(user).await()
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error getting user data")
        }
    }

    fun signOut() {
        auth.signOut()
    }
}