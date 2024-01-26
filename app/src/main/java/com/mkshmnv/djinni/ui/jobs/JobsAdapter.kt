package com.mkshmnv.djinni.ui.jobs

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.CardViewVacancyBinding
import com.mkshmnv.djinni.model.vacancy.Vacancy

class JobsAdapter(private var vacanciesList: MutableList<Vacancy>) :
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

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: ArrayList<Vacancy>) {
        vacanciesList = filterList
        notifyDataSetChanged()
    }

    class JobViewHolder(vacancy: View) : RecyclerView.ViewHolder(vacancy) {
        private val binding = CardViewVacancyBinding.bind(vacancy)
        fun bind(vacancy: Vacancy) = binding.apply {
            tvEmployerName.text = vacancy.employerName
            tvEmployerContact.text = vacancy.contactName
            tvEmployerContactPosition.text = vacancy.contactName
            tvVacancyTitle.text = vacancy.title
            tvVacancyResponded.visibility = View.INVISIBLE // TODO: Fix visibility
            tvVacancyPublishedDate.text = vacancy.publishedDate
            tvVacancyViews.text = vacancy.views.toString()
            tvVacancyReviews.text = vacancy.reviews.toString()
        }
    }
}