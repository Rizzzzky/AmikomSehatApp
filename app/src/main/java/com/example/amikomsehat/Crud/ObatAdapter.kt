package com.example.amikomsehat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ObatAdapter(private val obatList: MutableList<ObatModel>) :
    RecyclerView.Adapter<ObatAdapter.ObatViewHolder>() {

    class ObatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idObat: TextView = itemView.findViewById(R.id.textIdObat)
        val namaObat: TextView = itemView.findViewById(R.id.textNamaObat)
        val hargaObat: TextView = itemView.findViewById(R.id.textHargaObat)
        val imageObat: ImageView = itemView.findViewById(R.id.imageObat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObatViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_obat, parent, false)
        return ObatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ObatViewHolder, position: Int) {
        val currentObat = obatList[position]
        holder.idObat.text = currentObat.idObat.toString()
        holder.namaObat.text = currentObat.namaObat
        holder.hargaObat.text = currentObat.hargaObat
        holder.imageObat.setImageBitmap(currentObat.imageObat)
    }

    override fun getItemCount(): Int {
        return obatList.size
    }
    fun getItem(position: Int): ObatModel {
        return obatList[position]
    }
    fun removeItem(position: Int) {
        obatList.removeAt(position)
    }
}
