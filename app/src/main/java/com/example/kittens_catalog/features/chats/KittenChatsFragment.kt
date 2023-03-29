package com.example.kittens_catalog.features.chats

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentChatsBinding
import com.example.kittens_catalog.features.base.BaseFragment


class KittenChatsFragment : BaseFragment<FragmentChatsBinding>(R.layout.fragment_chats) {
    override val binding: FragmentChatsBinding by viewBinding()
    private val chatAdapter = KittenChatsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            chats.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            chats.adapter = chatAdapter
            chatAdapter.clickListener = {
                navigate(
                    R.id.kittenChatsFragment,
                    KittenChatsFragmentDirections.actionKittenChatsFragmentToKittenChatFragment()
                )
            }
            chatAdapter.setData(
                MockChat.list
            )
            addButton.setOnClickListener {
                val input = EditText(requireContext())
                input.inputType = InputType.TYPE_CLASS_TEXT
                val builder = AlertDialog.Builder(context)
                builder
                    .setMessage("Enter name whom you want to text")
                    .setCancelable(true)
                    .setView(input)
                    .setPositiveButton("Add") { a, b ->
                        MockChat.list.add(Message(name = input.text.toString(), value = "hello!"))
                        chatAdapter.setData(MockChat.list)
                    }
                builder.create().show()
            }
        }
    }
}