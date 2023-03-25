package com.example.kittens_catalog.features.kitten_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kittens_catalog.data.network.models.KittenItem
import com.example.kittens_catalog.databinding.KittenListItemBinding
import com.example.kittens_catalog.domain.interactors.AuthInteractor
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class KittenListAdapter:
    RecyclerView.Adapter<KittenListAdapter.KittenHolder>() {
    private val state: MutableList<KittenItem> = mutableListOf()
    lateinit var clickListener: (KittenItem) -> Unit

    fun setData(kittenList: List<KittenItem>?) {
        state.clear()
        if(kittenList != null)
            state.addAll(kittenList)
        notifyDataSetChanged()
    }

    inner class KittenHolder(private val binding: KittenListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KittenItem) {
            with(binding) {
                    itemText.text = item.name
                    val formatter = SimpleDateFormat("yyyy-MM-dd")
                    shortAbout.text = "Breed: ${item.breed}\nBirth Date: ${formatter.format(formatter.parse(item.birthDate))}"
                    Glide.with(root).load(item.picture).into(itemImage)
                    kittenItemBox.setOnClickListener {
                        clickListener.invoke(item)
                    }
                }
            }
        }

    override fun onBindViewHolder(holder: KittenHolder, position: Int) {
        val item = state[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { clickListener(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KittenHolder {
        val binding = KittenListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KittenHolder(binding)
    }

    override fun getItemCount(): Int {
        return state.size
    }
}