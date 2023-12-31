package com.example.amikomsehat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class DokterAdapter(private val dokterList: MutableList<DokterModel>) :
    RecyclerView.Adapter<DokterAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idDokter: TextView = itemView.findViewById(R.id.textIdDokter)
        val namaDokter: TextView = itemView.findViewById(R.id.textNamaDokter)
        val spesialis: TextView = itemView.findViewById(R.id.textSpesialisDokter)
        val imageDokter: ImageView = itemView.findViewById(R.id.imageDokter)
        val cvDokter: CardView = itemView.findViewById(R.id.cvDokter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_dokter, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val currentDokter = dokterList[position]
        holder.idDokter.text = currentDokter.id.toString()
        holder.namaDokter.text = currentDokter.namaDokter
        holder.spesialis.text = currentDokter.Spesialis
        holder.imageDokter.setImageBitmap(currentDokter.image)
        holder.cvDokter.setOnClickListener {
            val context = holder.itemView.context
            when (position) {
                0 -> context.startActivity(Intent(context, ProfilDokterActivity::class.java))
            }
        }}
        override fun getItemCount(): Int {
            return dokterList.size
        }

        fun getItem(position: Int): DokterModel {
            return dokterList[position]
        }

        fun removeItem(position: Int) {
            dokterList.removeAt(position)
        }
    }

