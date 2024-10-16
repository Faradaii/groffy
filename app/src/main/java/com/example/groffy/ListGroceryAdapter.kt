package com.example.groffy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.groffy.databinding.ItemRowGroceryBinding


class ListGroceryAdapter(private val listGrocery: ArrayList<Grocery>): RecyclerView.Adapter<ListGroceryAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    class ListViewHolder(var binding: ItemRowGroceryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowGroceryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listGrocery.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, price, nutrients) = listGrocery[position]
        Glide.with(holder.binding.cardView.context)
            .load(photo)
            .into(holder.binding.imageView)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.binding.tvItemNutrients.text = nutrients
        holder.binding.cardView.setOnClickListener {
            onItemClickCallback.onItemClicked(listGrocery[holder.adapterPosition])
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Grocery)
    }
}