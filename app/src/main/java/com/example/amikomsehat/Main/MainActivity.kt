package com.example.amikomsehat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMenu()
        setArtikel()
        setUsername()

    }

    private fun setUsername(){
        val username: String? = intent.getStringExtra("USERNAME_EXTRA")


        if (!username.isNullOrBlank()) {
            val textViewUsername: TextView = findViewById(R.id.textViewUsername)
            textViewUsername.text = "Selamat Datang, $username!"
        }
    }
    private fun setMenu(){
    val rvMenu: RecyclerView = findViewById(R.id.recylerMenu)
        rvMenu.setLayoutManager(GridLayoutManager(this, 2))
        rvMenu.setHasFixedSize(true)



    val dataMenu = ArrayList<MenuModel>()
    dataMenu.add(MenuModel(R.drawable.ic_chatdokter, "Chat Dokter"))
    dataMenu.add(MenuModel(R.drawable.ic_tokokesehatan, "Toko Kesehatan"))
    dataMenu.add(MenuModel(R.drawable.ic_artikel, "Artikel Kesehatan"))
    dataMenu.add(MenuModel(R.drawable.ic_buatjanji, "Buat Janji Offline"))
    val adapter1 = AdapterMenu(dataMenu)
    rvMenu.adapter = adapter1


}
private fun setArtikel(){
    val rvArtikel: RecyclerView = findViewById(R.id.recylerArtikel)
    rvArtikel.setLayoutManager(
        LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    )
    rvArtikel.setHasFixedSize(true)


        val dataArtikel = ArrayList<ArtikelModel>()
        dataArtikel.add(ArtikelModel(R.drawable.ic_makan,"Hidup Sehat","2200 Disukai"))
        dataArtikel.add(ArtikelModel(R.drawable.ic_demam,"Demam","2420 Disukai"))
        dataArtikel.add(ArtikelModel(R.drawable.ic_narkoba,"Penyalahgunaan Napza","1560 Disukai"))
        dataArtikel.add(ArtikelModel(R.drawable.ic_terapi,"Terapi Hormon","200 Disukai"))

        val adapter2 = AdapterArtikel(dataArtikel)
        rvArtikel.adapter = adapter2
    }

}



