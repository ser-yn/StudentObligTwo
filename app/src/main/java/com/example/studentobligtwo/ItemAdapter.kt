package com.example.studentobligtwo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentobligtwo.Database.EntryEntity

class ItemAdapter:RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var entryList = emptyList<EntryEntity>()

    //Since viewHolder is only used inside ItemAdapter, we can make it nested to show that relationship
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val textViewTwo: TextView = view.findViewById(R.id.item_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.entry_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = entryList[position]
        holder.textView.text = currentItem.name
        holder.textViewTwo.text = currentItem.imageResource
    }

    override fun getItemCount(): Int = entryList.size

    fun setData(entry: List<EntryEntity>){
        this.entryList = entry
        notifyDataSetChanged()
    }
}