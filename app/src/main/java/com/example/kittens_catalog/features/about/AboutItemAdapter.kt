package com.example.kittens_catalog.features.about

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kittens_catalog.databinding.FragmentAboutItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class AboutItemAdapter : RecyclerView.Adapter<AboutItemAdapter.AboutHolder>() {
    private val state: MutableList<AboutInfo> = mutableListOf()
    lateinit var clickListener: (AboutInfo) -> Unit
    fun setData(kittenList: List<AboutInfo>?) {
        state.clear()
        if (kittenList != null)
            state.addAll(kittenList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutHolder {

        return AboutHolder(
            FragmentAboutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }
    override fun onBindViewHolder(holder: AboutHolder, position: Int) {
        val item = state[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { clickListener(item) }
    }
    inner class AboutHolder(private val binding: FragmentAboutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AboutInfo) {
            with(binding) {
                    content.text = item.name
                }
            }
        }
    override fun getItemCount(): Int {
        return state.size
    }
}

data class AboutInfo(
    var name: String
)