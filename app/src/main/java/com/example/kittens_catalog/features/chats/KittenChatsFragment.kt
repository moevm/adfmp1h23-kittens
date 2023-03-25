package com.example.kittens_catalog.features.chats

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentChatsBinding
import com.example.kittens_catalog.features.base.BaseFragment

class KittenChatsFragment:BaseFragment<FragmentChatsBinding>(R.layout.fragment_chats) {
    override val binding: FragmentChatsBinding by viewBinding()
    private val chatAdapter = KittenChatsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding){
            chats.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            chats.adapter = chatAdapter
            chatAdapter.setData(
                MockChat.list
            )
        }
    }
}