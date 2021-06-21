package com.gg.petdoc_project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gg.petdoc_project.Dtos.ChatDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chat.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ChatActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    val user = FirebaseAuth.getInstance().currentUser
    var menu: String = "etc"
    var content:String?="null"
    lateinit var chatDto: ChatDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }

    override fun onStart() {
        super.onStart()
        chat_act.setOnClickListener { menu = "행동" }
        chat_nutrition.setOnClickListener { menu = "영양" }
        chat_jub.setOnClickListener { menu = "예방접종" }
        chat_jehwal.setOnClickListener { menu = "재활" }
        chat_susul.setOnClickListener { menu = "수술" }
        chat_etc.setOnClickListener { menu="기타" }
        chat_start.setOnClickListener {
            var msg = chat_content.text.toString()
            message(msg,menu)
            finish()
        }
    }
    fun message(message:String,menu:String){
        var time =LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        database=Firebase.database.getReference("ChatBoard")
        chatDto= ChatDto(
            user?.displayName.toString(),
            time.toString(),
            message
        )
        database.child(menu).child(chatDto.chat_username).setValue(chatDto)
        Log.d("채팅정보확인",""+ "," + chatDto.chat_username + ","+ chatDto.chat_content)

    }

}
