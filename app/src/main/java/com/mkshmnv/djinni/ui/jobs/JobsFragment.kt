package com.mkshmnv.djinni.ui.jobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.CardViewVacancyBinding
import com.mkshmnv.djinni.databinding.FragmentJobsBinding
import com.mkshmnv.djinni.model.employer.Categories
import com.mkshmnv.djinni.model.employer.Country
import com.mkshmnv.djinni.model.employer.Domains
import com.mkshmnv.djinni.model.employer.Employer
import com.mkshmnv.djinni.model.employer.EmployerContact
import com.mkshmnv.djinni.model.employer.EmploymentOption
import com.mkshmnv.djinni.model.employer.EnglishLevel
import com.mkshmnv.djinni.model.employer.ExperienceLevel
import com.mkshmnv.djinni.model.employer.TypeOfEmployer
import com.mkshmnv.djinni.model.employer.Vacancy
import com.mkshmnv.djinni.ui.viewBinding

class JobsFragment : Fragment(R.layout.fragment_jobs) {
    private val binding: FragmentJobsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

        val employerContact = EmployerContact(
            1,
            "Name",
            "photo",
            "Position",
            true,
            true,
            "23.01.2024",
            "Activity",
            Country.UKRAINE
        )

        val employer = Employer(
            "Name",
            "Logo",
            "Description",
            "Website",
            TypeOfEmployer.PRODUCT
        )
//        binding.rvVacancies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVacancies.adapter = JobsAdapter(
            mutableListOf(
                Vacancy(
                    1,
                    employer,
                    employerContact,
                    "Title",
                    "Published date",
                    0,
                    0,
                    description = "Description",
                    EnglishLevel.NO_ENGLISH,
                    100000,
                    Categories.MOBILE,
                    ExperienceLevel.SIX_MONTHS,
                    listOf(Domains.GAMBLING, Domains.GAME_DEV),
                    listOf(EmploymentOption.OFFICE, EmploymentOption.REMOTE),
                    Country.UKRAINE,
                    listOf(Country.UKRAINE)
                ),
                Vacancy(
                    2,
                    employer,
                    employerContact,
                    "Title2",
                    "Published date2",
                    0,
                    0,
                    description = "Description2",
                    EnglishLevel.NO_ENGLISH,
                    100000,
                    Categories.MOBILE,
                    ExperienceLevel.SIX_MONTHS,
                    listOf(Domains.GAMBLING, Domains.GAME_DEV),
                    listOf(EmploymentOption.OFFICE, EmploymentOption.REMOTE),
                    Country.UKRAINE,
                    listOf(Country.UKRAINE)
                ),
                Vacancy(
                    3,
                    employer,
                    employerContact,
                    "Title3",
                    "Published date3",
                    0,
                    0,
                    description = "Description3",
                    EnglishLevel.NO_ENGLISH,
                    100000,
                    Categories.MOBILE,
                    ExperienceLevel.SIX_MONTHS,
                    listOf(Domains.GAMBLING, Domains.GAME_DEV),
                    listOf(EmploymentOption.OFFICE, EmploymentOption.REMOTE),
                    Country.UKRAINE,
                    listOf(Country.UKRAINE)
                )
            )
        )
    }
}

class JobsAdapter(private val vacanciesList: MutableList<Vacancy>) :
    RecyclerView.Adapter<JobsAdapter.JobViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_vacancy, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(vacanciesList[position])
    }

    override fun getItemCount() = vacanciesList.size

    class JobViewHolder(vacancy: View) : RecyclerView.ViewHolder(vacancy) {
        private val binding = CardViewVacancyBinding.bind(vacancy)
        fun bind(vacancy: Vacancy) = binding.apply {
            tvEmployerName.text = vacancy.employer.name
            tvEmployerContact.text = vacancy.contact.name
            tvEmployerContactPosition.text = vacancy.contact.position
            tvVacancyTitle.text = vacancy.title
            tvVacancyResponded.visibility = View.INVISIBLE // TODO: Fix visibility
            tvVacancyPublishedDate.text = vacancy.publishedDate
            tvVacancyViews.text = vacancy.views.toString()
            tvVacancyReviews.text = vacancy.reviews.toString()
            tvVacancyLocation.text = vacancy.location.name
            tvVacancyEmploymentType.text = vacancy.employmentType.toString() // TODO: FIX
            tvVacancyExperience.text = vacancy.experience.toString() // TODO: FIX
            tvVacancyEnglishLevel.text = vacancy.english.toString() // TODO: FIX
            tvVacancyDescription.text = vacancy.description
        }
    }
}