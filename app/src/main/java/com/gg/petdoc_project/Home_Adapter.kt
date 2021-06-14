package com.gg.petdoc_project

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.homeimgview.view.*


class Home_Adapter(list: ArrayList<Int>)
    : RecyclerView.Adapter<Home_Adapter.PagerViewHolder>() {
    var item = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =PagerViewHolder((parent))

    override fun getItemCount(): Int =item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.img.setImageResource(item[position])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.homeimgview, parent, false)){
        val img: ImageView = itemView.homeimage_view
    }

}