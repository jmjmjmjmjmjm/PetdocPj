package com.gg.petdoc_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gg.petdoc_project.Dtos.ChatDto
import com.gg.petdoc_project.adapters.ChatAdpter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chatting.*


class Chatting : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()
    var mychatList = ArrayList<ChatDto>()
    var yourchatList = ArrayList<ChatDto>()
    var myRef = database.getReference("ChatBoard").child("행동")
    var menu = "행동"
    val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
    }

    override fun onStart() {
        super.onStart()
        chatting_act.setOnClickListener { menu = "행동" }
        chatting_nutrition.setOnClickListener { menu = "영양" }
        chatting_jub.setOnClickListener { menu = "예방접종" }
        chatting_jehwal.setOnClickListener { menu = "재활" }
        chatting_susul.setOnClickListener { menu = "수술" }
        chatting_etc.setOnClickListener { menu = "기타" }
        chatting_exit.setOnClickListener { finish() }
        childEventListenr(myRef)
        chatting_btn.setOnClickListener {
            val msg = chatting_msg.text.toString()
            ChatActivity().message(msg,menu)
            chatting_msg.setText("")    }

    }


    fun childEventListenr(databaseReference: DatabaseReference) {
        val childEventListener = object : ChildEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("칠드이벤트리스너", "바뀜" + snapshot.value)
                var info = snapshot.value.toString()
                var infolist = info.split(",", "=", "}")
                mychatList.add(
                    ChatDto(
                        infolist[1],
                        infolist[5],
                        infolist[3]
                    )
                )
                val adapter = ChatAdpter(
                    mychatList,
                    LayoutInflater.from(this@Chatting),
                    user
                )
                chatting_view.adapter = adapter
                chatting_view.layoutManager = LinearLayoutManager(this@Chatting)
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("칠드이벤트리스너", "추가됨" + snapshot.value)
                var info = snapshot.value.toString()
                var infolist = info.split(",", "=", "}")
                mychatList.add(
                    ChatDto(
                        infolist[1],
                        infolist[5],
                        infolist[3]
                    )
                )
                val adapter = ChatAdpter(
                    mychatList,
                    LayoutInflater.from(this@Chatting),
                    user
                )
                chatting_view.adapter = adapter
                chatting_view.layoutManager = LinearLayoutManager(this@Chatting)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.d("칠드이벤트리스너", "삭제됨" + snapshot)
            }

        }
        databaseReference.addChildEventListener(childEventListener)
    }
}