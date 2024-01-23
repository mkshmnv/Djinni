package com.mkshmnv.djinni.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mkshmnv.djinni.model.user.User
import kotlinx.coroutines.tasks.await

class UserRepository {
    // Firebase instances
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference.child("users")

    // Create user in Firebase Realtime Database
    suspend fun registrationUser(email: String, password: String): Resource<User> {
        return try {
            val auth = auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(uid = auth.user?.uid.toString(), contactsEmail = email)
            database.child(user.uid).setValue(user)
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Registration failed")
        }
    }

    // Auth user in database
    suspend fun loginUser(email: String, password: String): Resource<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val userID = authResult.user?.uid.toString()
            try {
                val snapshot = database.child(userID).get().await()
                val userData = snapshot.getValue(User::class.java)
                    ?: throw Exception("User not found")
                Resource.Success(userData)
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Error getting user data for $userID")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "SignInWithEmailAndPassword failed")
        }
    }

    // Update user data from UI to database
    suspend fun updateUser(user: User): Resource<User> {
        return try {
            database.child(user.uid).setValue(user).await()
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error getting user data")
        }
    }

    // Sign out user
    fun signOut(user: User): Resource<User> {
        return try {
            auth.signOut()
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error signing out")
        }
    }

    // Delete user from database
    suspend fun deleteUser(user: User): Resource<User> {
        return try {
            database.child(user.uid).setValue(null).await()
            auth.currentUser?.delete()?.await()
            auth.signOut()
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error getting user data")
        }
    }

    suspend fun raiseUser(user: User): Resource<User> {
        return try {
            // TODO: Implement raise
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error getting user data")
        }
    }
}