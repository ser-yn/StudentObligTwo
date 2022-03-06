package com.example.studentobligtwo

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.studentobligtwo.Database.EntryEntity

class ItemAdapter:RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var entryList = emptyList<EntryEntity>()

    //Since viewHolder is only used inside ItemAdapter, we can make it nested to show that relationship
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.entry_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = entryList[position]
        holder.textView.text = currentItem.name
        holder.imageView.setImageURI(currentItem.imageResource)
    }

    override fun getItemCount(): Int = entryList.size

    fun setData(entry: MutableList<EntryEntity>){
        this.entryList = entry
        notifyDataSetChanged()
    }
}