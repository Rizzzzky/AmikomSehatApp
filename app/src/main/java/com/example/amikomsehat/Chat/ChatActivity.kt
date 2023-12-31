package com.example.amikomsehat

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {

    private val chatMessages: MutableList<ChatMessage> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val rvChat: RecyclerView = findViewById(R.id.chatRecyclerView)
        val layoutManager = LinearLayoutManager(this)

        rvChat.layoutManager = layoutManager

        val chatAdapter = ChatAdapter(chatMessages)
        rvChat.adapter = chatAdapter

        val sendButton: ImageView = findViewById(R.id.buttontSend)
        val inputMessage: EditText = findViewById(R.id.inputMessage)

        sendButton.setOnClickListener {
            val messageText = inputMessage.text.toString().trim()
            if (messageText.isNotEmpty()) {

                val userMessage = ChatMessage(messageText, isUser = 1)

                chatAdapter.addMessage(userMessage)

                inputMessage.text.clear()
            }
        }
    }
}
