package com.example.myquotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class QuotesAdapter(val listReview: ArrayList<String>) : RecyclerView.Adapter<QuotesAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
        val item = itemView.findViewById<TextView>(R.id.item_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_quotes, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listReview.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.item.text = listReview[position]
    }
}