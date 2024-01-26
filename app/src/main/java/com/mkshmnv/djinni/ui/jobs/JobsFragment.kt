package com.mkshmnv.djinni.ui.jobs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.Toast
import com.mkshmnv.djinni.databinding.FragmentJobsBinding
import com.mkshmnv.djinni.model.vacancy.Vacancy
import com.mkshmnv.djinni.repository.user.UserViewModel
import com.mkshmnv.djinni.repository.vacancies.VacancyViewModel
import com.mkshmnv.djinni.ui.viewBinding

class JobsFragment : Fragment(R.layout.fragment_jobs) {
    private val tag = this::class.simpleName!!
    private val binding: FragmentJobsBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    private val vacancyViewModel: VacancyViewModel by activityViewModels()
    private lateinit var adapter: JobsAdapter
    private lateinit var vacancies: MutableList<Vacancy>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", tag)
        adapter = JobsAdapter(mutableListOf())
        val user = userViewModel.authorizedUser.value ?: throw Exception("User is not authorized")
        vacancyViewModel.apply {
            getVacancies(user.profileCategory)
            vacanciesList.observe(viewLifecycleOwner) {
                if (it != null) vacancies = it.toMutableList()
                adapter = JobsAdapter(vacancies)
                binding.rvVacancies.adapter = adapter
            }
            binding.svVacancies.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Logger.logcat("onQueryTextSubmit", tag)
                    return false
                }

                override fun onQueryTextChange(searchText: String): Boolean {
                    Logger.logcat("onQueryTextChange", tag)
                    filter(searchText)
                    return false
                }
            })
        }
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<Vacancy> = ArrayList()
        for (item in vacancies) {
            // TODO: Fix filter
            if (item.title.lowercase().contains(text.lowercase())) {
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.show("No Data Found..")
        } else {
            adapter.filterList(filteredList)
        }
    }
}