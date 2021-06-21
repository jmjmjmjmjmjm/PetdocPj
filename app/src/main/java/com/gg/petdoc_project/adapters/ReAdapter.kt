package com.gg.petdoc_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gg.petdoc_project.Dtos.BoardDto
import com.gg.petdoc_project.R
import com.gg.petdoc_project.read_info


class ReAdapter(
    val itemList: ArrayList<BoardDto>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<ReAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dataImg: ImageView? = null
        var dataTitle: TextView? = null
        var dataName: TextView? = null

        init {
            dataImg = view.findViewById<ImageView>(R.id.re_img)
            dataTitle = view.findViewById<TextView>(R.id.re_title)
            dataName = view.findViewById<TextView>(R.id.re_name)
            itemView.setOnClickListener {
                val context = itemView.context
                val position=adapterPosition
                val title = itemList.get(position).title
                val content = itemList.get(position).content
                val name = itemList.get(position).username
                val date = itemList.get(position).createDate
                val intent = Intent(context,read_info()::class.java)
                intent.putExtra("title",title)
                intent.putExtra("content",content)
                intent.putExtra("name",name)
                intent.putExtra("date",date)
                ContextCompat.startActivity(context,intent,null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.recy_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var url ="https://firebasestorage.googleapis.com/v0/b/petdoc-17950.appspot.com/o/images%2F"+itemList[position].title+"?alt=media&token"
        Glide.with(holder.itemView.context)
            .load(url)
            .fitCenter()
            .override(210,210)
            .into(holder.dataImg!!)
        holder.dataTitle?.text = itemList[position].title
        holder.dataName?.text = itemList[position].username
    }


}