package com.gg.petdoc_project.Dtos

import android.widget.ImageView

class BoardDto(
    val uid:String,
    val username:String,
    val anymal:String?,
    val title:String,
    val content:String,
    val img:String,
    val createDate:String) {
}