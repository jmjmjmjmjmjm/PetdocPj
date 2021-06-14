package com.gg.petdoc_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_read_info.*

class read_info() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_info)
        
        val name = intent.getStringExtra("name")
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val date = intent.getStringExtra("date")

        readinfo_title.text = title
        readinfo_date.text=date+" "+name
        readinfo_content.text=content
        var url ="https://firebasestorage.googleapis.com/v0/b/petdoc-17950.appspot.com/o/images%2F"+title+"?alt=media&token"
        Glide.with(this)
            .load(url)
            .fitCenter()
            .into(readinfo_img)
    }

    override fun onStart() {
        super.onStart()
        readinfo_close.setOnClickListener { finish() }
    }
}