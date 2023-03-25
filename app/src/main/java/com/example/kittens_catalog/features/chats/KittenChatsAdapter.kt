package com.example.kittens_catalog.features.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kittens_catalog.data.network.models.KittenItem
import com.example.kittens_catalog.databinding.KittenChatItemBinding

class KittenChatsAdapter:
    RecyclerView.Adapter<KittenChatsAdapter.ChatHolder>() {
    private val state: MutableList<Message> = mutableListOf()
    lateinit var clickListener: () -> Unit


    fun setData(kittenList: List<Message>) {
        state.clear()
        state.addAll(kittenList)
        notifyDataSetChanged()
    }

    inner class ChatHolder(private val binding: KittenChatItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Message, clearChat: () -> Unit) {
            with(binding) {
                    name.text = item.name
                    message.text = item.value
                    clear.setOnClickListener {
                        clearChat()
                    }
                    binding.kittenChatBox.setOnClickListener {
                        clickListener.invoke()
                    }
                }
            }
        }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val item = state[position]
        holder.bind(item){
            state.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val binding = KittenChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatHolder(binding)
    }

    override fun getItemCount(): Int {
        return state.size
    }
}