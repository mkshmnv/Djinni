package com.mkshmnv.djinni.ui.inbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mkshmnv.djinni.R

//class InboxAdapter : RecyclerView.Adapter<InboxAdapter.InboxViewHolder>() {
//    val data = mutableListOf<MyData>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
//        // Інфляція макету елемента списку
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
//        return InboxViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
//        // Встановлення даних для елемента списку
//        holder.itemView.textView.text = data[position].text
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    class InboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView: TextView = itemView.findViewById(R.id.text_view)
//    }
//}
//
//data class MyData(val text: String)