package com.example.kittens_catalog.features.chats

object MockChat {
    val list: MutableList<Message> = mutableListOf(
        Message(
            "hello",
            "Masha"
        ),
        Message(
            "hello",
            "Test"
        ),
        Message(
            "hello",
            "Borya"
        ),
    )
}

data class Message(
    val value: String,
    val name: String,
)