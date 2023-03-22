package com.example.kittens_catalog.features.kitten_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kittens_catalog.data.network.models.KittenItem
import com.example.kittens_catalog.databinding.KittenListItemBinding
import com.example.kittens_catalog.domain.interactors.AuthInteractor

class KittenListAdapter :
    RecyclerView.Adapter<KittenListAdapter.KittenHolder>() {
    private val state: MutableList<KittenItem> = mutableListOf()

    fun setData(kittenList: List<KittenItem>?) {
        state.clear()
        if(kittenList != null)
            state.addAll(kittenList)
//        notifyDataSetChanged()
    }

    inner class KittenHolder(private val binding: KittenListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KittenItem) {
            with(binding) {
                    itemText.text = item.name
                    Glide.with(root).load(item.picture).into(itemImage);
                }
            }
        }

    override fun onBindViewHolder(holder: KittenHolder, position: Int) {
        val item = state[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KittenHolder {
        val binding = KittenListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KittenHolder(binding)
    }

    override fun getItemCount(): Int {
        return state.size
    }
}