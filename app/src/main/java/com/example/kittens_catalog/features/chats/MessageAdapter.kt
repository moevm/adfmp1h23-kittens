package com.example.kittens_catalog.features.chats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kittens_catalog.databinding.MyMessageBinding
import com.example.kittens_catalog.databinding.OtherMessageBinding

private const val VIEW_TYPE_MY_MESSAGE = 1
private const val VIEW_TYPE_OTHER_MESSAGE = 2

class MessageAdapter (val context: Context) : RecyclerView.Adapter<MessageViewHolder>() {
    private val messages: ArrayList<Message> = ArrayList()

    fun addMessage(message: Message){
        messages.add(message)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages.get(position)

        return if("boris" == message.name) {
            VIEW_TYPE_MY_MESSAGE
        }
        else {
            VIEW_TYPE_OTHER_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType == VIEW_TYPE_MY_MESSAGE) {
            MyMessageViewHolder(MyMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            OtherMessageViewHolder(OtherMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages.get(position)

        holder?.bind(message)
    }

    inner class MyMessageViewHolder (private val binding: MyMessageBinding) : MessageViewHolder(binding.root) {
        private var messageText: TextView = binding.txtMyMessage
        private var timeText: TextView = binding.txtMyMessageTime

        override fun bind(message: Message) {
            messageText.text = message.value
        }
    }

    inner class OtherMessageViewHolder (private val binding:OtherMessageBinding ) : MessageViewHolder(binding.root) {
        override fun bind(message: Message) {
            binding.txtOtherMessage.text = message.value
            binding.txtOtherUser.text = message.name
        }
    }
}

open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(message:Message) {}
}