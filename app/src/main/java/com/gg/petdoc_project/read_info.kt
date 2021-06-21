package com.gg.petdoc_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_read_info.*

class read_info() : AppCompatActivity() {
    private var title:String?=null
    private var name:String?=null
    private var content:String?=null
    private var date:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_info)
        
        name = intent.getStringExtra("name")
        title = intent.getStringExtra("title")
        content = intent.getStringExtra("content")
        date = intent.getStringExtra("date")
        detail()

    }

    override fun onStart() {
        super.onStart()
        readinfo_close.setOnClickListener { finish() }
    }

    fun detail(){
        readinfo_title.text = title
        readinfo_date.text=date+" "+name
        readinfo_content.text=content
        var url ="https://firebasestorage.googleapis.com/v0/b/petdoc-17950.appspot.com/o/images%2F"+title+"?alt=media&token"
        Glide.with(this)
            .load(url)
            .fitCenter()
            .into(readinfo_img)
    }
}