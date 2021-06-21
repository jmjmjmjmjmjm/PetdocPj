package com.gg.petdoc_project.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gg.petdoc_project.Dtos.ChatDto
import com.gg.petdoc_project.R

class ChatAdpter(
    val itemList: ArrayList<ChatDto>,
    val inflater: LayoutInflater,
    val user: String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val youchat_content: TextView = itemView.findViewById(R.id.youchat_content)
        val youchat_date: TextView? = itemView.findViewById(R.id.youchat_date)

        fun bind(item: ChatDto) {
            youchat_content.text = item.chat_content
            youchat_date?.text = item.chat_createDate
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList[position].chat_username == "여포방구석") 0 else 1
    }

    inner class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chat_content: TextView = itemView.findViewById(R.id.chat_content)
        val chat_date: TextView? = itemView.findViewById(R.id.chat_date)

        fun bind(item: ChatDto) {
            chat_content.text = item.chat_content
            chat_date?.text = item.chat_createDate
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val view = inflater.inflate(R.layout.chat_youitem, parent, false)
            return ViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.chat_myitem, parent, false)
            return ViewHolder2(view)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            (holder as ViewHolder).bind(itemList[position])
            holder.setIsRecyclable(false)
        } else if (holder is ViewHolder2) {
            (holder as ViewHolder2).bind(itemList[position])
            holder.setIsRecyclable(false)
        }
    }
}