package com.mkshmnv.djinni.repository.vacancies

import com.google.firebase.database.FirebaseDatabase
import com.mkshmnv.djinni.model.vacancy.Vacancy
import com.mkshmnv.djinni.repository.Resource
import kotlinx.coroutines.tasks.await


class VacancyRepository {
    private val database = FirebaseDatabase.getInstance().reference.child("vacancies")

    suspend fun dataList(category: String): Resource<List<Vacancy>> {
        return try {
            val snapshot = database.child(category).get().await()
            val employersList = mutableListOf<Vacancy>()
            for (childSnapshot in snapshot.children) {
                val data = childSnapshot.getValue(Vacancy::class.java)
                data?.let { employersList.add(it) }
            }
            Resource.Success(employersList)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error getting employers data")
        }
    }
}