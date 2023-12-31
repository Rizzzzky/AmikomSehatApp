package com.example.amikomsehat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView



class AdapterArtikel(ArtikelList: List<ArtikelModel>) :
    RecyclerView.Adapter<AdapterArtikel.ViewHolder>() {
    private val ArtikelList: List<ArtikelModel>

    init {
        this.ArtikelList = ArtikelList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_artikel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ArtikelModel = ArtikelList[position]
        holder.imgThumb.setImageResource(ArtikelModel.image);
        holder.tvPlaceName.setText(ArtikelModel.namaArtikel);
        holder.tvVote.setText(ArtikelModel.vote)
    }


    override fun getItemCount(): Int {
        return ArtikelList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvArtikel: CardView
        var tvPlaceName: TextView
        var imgThumb: ImageView
        var tvVote: TextView

        init {
            cvArtikel = itemView.findViewById<CardView>(R.id.cvArtikel)
            tvPlaceName = itemView.findViewById<TextView>(R.id.tvPlaceName)
            imgThumb = itemView.findViewById<ImageView>(R.id.imgThumb)
            tvVote =itemView.findViewById<TextView>(R.id.tvVote)
        }
    }
}