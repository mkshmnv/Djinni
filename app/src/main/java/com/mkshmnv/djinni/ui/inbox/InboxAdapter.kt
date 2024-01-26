package com.mkshmnv.djinni.ui.inbox

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