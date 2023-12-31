package com.example.amikomsehat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView



public class AdapterCrud (crudList: List<CrudModel>) :
    RecyclerView.Adapter<AdapterCrud.ViewHolder>() {
    private val crudList: List<CrudModel>;

    init {

        this.crudList = crudList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crudModel = crudList[position]
        holder.imageIcon.setImageResource(crudModel.icon1);
        holder.tvName.setText(crudModel.namecrud);

        holder.cvmenu.setOnClickListener{
            val context = holder.itemView.context
            when (position) {
                0 -> context.startActivity(Intent(context, DokterActivity::class.java))
                1 -> context.startActivity(Intent(context, ObatActivity::class.java))
            }
        }}


    override fun getItemCount(): Int {
        return crudList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvmenu: CardView
        var tvName: TextView
        var imageIcon: ImageView

        init {
            cvmenu = itemView.findViewById<CardView>(R.id.cvMenu)
            tvName = itemView.findViewById<TextView>(R.id.tvName)
            imageIcon = itemView.findViewById<ImageView>(R.id.imageIcon)
        }
    }
}