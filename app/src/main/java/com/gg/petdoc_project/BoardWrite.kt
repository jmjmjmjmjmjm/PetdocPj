package com.gg.petdoc_project

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.graphics.drawable.toIcon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_board_write.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class BoardWrite : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private var anymal: String? = ""
    private var fbStorage: FirebaseStorage? = null
    private val user = FirebaseAuth.getInstance().currentUser
    private var imgurl: String? = null
    private var boardDto: BoardDto? = null
    private var url: Uri? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)
        db = Firebase.firestore
        fbStorage = FirebaseStorage.getInstance()

    }

    override fun onStart() {
        super.onStart()
        write_cat.setOnClickListener { anymal = "cat" }
        write_dog.setOnClickListener { anymal = "dog" }
        write_img.setOnClickListener { openGallery() }
        write_close.setOnClickListener { finish() }
        write_excute.setOnClickListener {
            info()
            finish()
        }
    }

    fun info() {
        var uid = user?.uid.toString()
        var username = user?.displayName.toString()
        val any = anymal.toString()
        title = write_title.text.toString()
        val content = write_content.text.toString()
        var createDate = LocalDateTime.now().toString()
        boardDto = username?.let {
            BoardDto(
                uid.toString(),
                it,
                any.toString(),
                title.toString(),
                content,
                imgurl.toString(),
                createDate
            )
        }
        Log.d(
            "Dto확인",
            "" + boardDto?.uid + "\n" + boardDto?.username + "\n" + boardDto?.anymal + "\n" + boardDto?.title + "\n" + boardDto?.content + "\n" + boardDto?.img + "\n" + boardDto?.createDate
        )
        upload()
        boardDto?.let { db.collection("board").add(it) }
    }

    fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, 500)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 500) {
                url = data?.data

                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, url)
                write_img.setImageBitmap(bitmap)
                Log.d("이미지확인", "" + bitmap)
            }
        }
    }

    fun upload() {
        var storageRef = fbStorage?.reference?.child("images")?.child(title.toString())
        Log.d("스토리지 확인", "" + storageRef.toString())
        imgurl = "/images"
        if (url != null) {
            storageRef?.putFile(url!!)
        }
    }

}