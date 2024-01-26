package com.mkshmnv.djinni.repository.employer

import com.google.firebase.database.FirebaseDatabase
import com.mkshmnv.djinni.model.employer.Employer
import com.mkshmnv.djinni.repository.Resource
import kotlinx.coroutines.tasks.await

class EmployerRepository {
    private val database = FirebaseDatabase.getInstance().reference.child("employers")

    suspend fun dataList(): Resource<List<Employer>> {
        return try {
            val snapshot = database.get().await()
            val employersList = mutableListOf<Employer>()
            for (childSnapshot in snapshot.children) {
                val data = childSnapshot.getValue(Employer::class.java)
                data?.let { employersList.add(it) }
            }
            Resource.Success(employersList)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error getting employers data")
        }
    }
}