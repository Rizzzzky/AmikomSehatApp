package com.example.amikomsehat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView



public class AdapterMenu(MenuList: List<MenuModel>) :
    RecyclerView.Adapter<AdapterMenu.ViewHolder>() {
    private val MenuList: List<MenuModel>;

    init {

        this.MenuList = MenuList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val MenuModel = MenuList[position]
        holder.imageIcon.setImageResource(MenuModel.icon);
        holder.tvName.setText(MenuModel.name);

        holder.cvMenu.setOnClickListener{
            val context = holder.itemView.context
            when (position) {
                0 -> context.startActivity(Intent(context, ChatDokterActivity::class.java))
                1 -> context.startActivity(Intent(context, OrderActivity::class.java))
                2 -> context.startActivity(Intent(context, LabActivity::class.java))
                3 -> context.startActivity(Intent(context, BuatJanjiActivity::class.java))
        }
    }}


override fun getItemCount(): Int {
    return MenuList.size
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var cvMenu: CardView
    var tvName: TextView
    var imageIcon: ImageView

    init {
        cvMenu = itemView.findViewById<CardView>(R.id.cvMenu)
        tvName = itemView.findViewById<TextView>(R.id.tvName)
        imageIcon = itemView.findViewById<ImageView>(R.id.imageIcon)
    }
}
}