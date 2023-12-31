package com.example.amikomsehat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CrudActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
        setCrud()
        setAdmin()
    }
    private fun setAdmin(){
        val isAdmin = intent.getBooleanExtra("IS_ADMIN", false)

        if (isAdmin) {
            val textViewAdmin: TextView = findViewById(R.id.textViewAdmin)
            textViewAdmin.text = "Admin AmikomSehat"
        } else {

        }

    }
    private fun setCrud(){
        val rvCrud: RecyclerView = findViewById(R.id.recylerCrud)
        rvCrud.setLayoutManager(GridLayoutManager(this, 2))
        rvCrud.setHasFixedSize(true)



        val dataCrud = ArrayList<CrudModel>()
        dataCrud.add(CrudModel(R.drawable.ic_chatdokter, "Chat Dokter"))
        dataCrud.add(CrudModel(R.drawable.ic_tokokesehatan, "Toko Kesehatan"))
        dataCrud.add(CrudModel(R.drawable.ic_artikel, "Artikel Kesehatan"))
        dataCrud.add(CrudModel(R.drawable.ic_buatjanji, "Buat Janji Offline"))
        val adapter1 = AdapterCrud(dataCrud)
        rvCrud.adapter = adapter1


    }
    }