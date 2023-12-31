package com.example.amikomsehat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProfilDokterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_dokter)

        val buttonChat: Button = findViewById(R.id.buttonChat)

        buttonChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)

            // Start the ChatActivity
            startActivity(intent)
        }
    }
}
