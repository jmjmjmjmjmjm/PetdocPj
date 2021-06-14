package com.gg.petdoc_project

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.UiThread
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.gg.petdoc_project.Frag.ReAdapter
import com.gg.petdoc_project.Frag.shop_Frag
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home_.*
import kotlinx.android.synthetic.main.fragment_shop_.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

    }

    override fun onStart() {

        super.onStart()
        Thread(Runnable {
            Thread.sleep(1000)
            val intent= Intent(this@MainActivity2,HomeActivity::class.java)
            finish()
            startActivity(intent)
        }).start()

    }


}