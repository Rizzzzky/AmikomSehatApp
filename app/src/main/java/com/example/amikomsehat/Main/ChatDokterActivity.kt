package com.example.amikomsehat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatDokterActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DokterAdapter //



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_dokter)

        dbHelper = DatabaseHelper(this)

        recyclerView = findViewById(R.id.recyclerDokter)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val dokterList = dbHelper.showMenu()

        adapter = DokterAdapter(dokterList)
        recyclerView.adapter = adapter
    }
}