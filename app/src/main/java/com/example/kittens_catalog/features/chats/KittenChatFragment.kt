package com.example.kittens_catalog.features.chats

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.ChatFragmentBinding
import com.example.kittens_catalog.features.base.BaseFragment

class KittenChatFragment:BaseFragment<ChatFragmentBinding>(R.layout.chat_fragment) {
    override val binding: ChatFragmentBinding by viewBinding()
    private val adapterChat by lazy {
        MessageAdapter(
            requireContext()
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.messageList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.messageList.adapter = adapterChat
        binding.btnSend.setOnClickListener{
            adapterChat.addMessage(
                Message(
                    binding.txtMessage.text.toString(),
                    "boris"
                )
            )
            binding.txtMessage.setText("")
        }
    }
}