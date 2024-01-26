package com.mkshmnv.djinni.repository.vacancies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.model.vacancy.Vacancy
import com.mkshmnv.djinni.repository.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VacancyViewModel(application: Application) : AndroidViewModel(application) {
    // Get repository
    private val repository = VacancyRepository()

    // LiveData Vacancies
    private var _vacanciesList = MutableLiveData<List<Vacancy>?>()
    val vacanciesList: LiveData<List<Vacancy>?> = _vacanciesList

    fun getVacancies(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.dataList(category)
            when (result) {
                is Resource.Success -> {
                    val vacancies = result.data
                    _vacanciesList.postValue(vacancies)
                }

                is Resource.Error -> Logger.logcat(result.message, "getVacancies")
                else -> Logger.logcat(R.string.error.toString(), "getVacancies")
            }
        }
    }
}