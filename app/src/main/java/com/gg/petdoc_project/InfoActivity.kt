package com.gg.petdoc_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.fragment_home_.*

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d("로그인이 확인됨.", "로그인완료")
            val user = FirebaseAuth.getInstance().currentUser
            username.setText(user?.displayName)
            logout.setOnClickListener { logout() }
            chatting_list.setOnClickListener {
                val intent = Intent(this@InfoActivity, Chatting::class.java)
                startActivity(intent)
            }
        } else {
            login()
            finish()
        }
    }
    fun logout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                // ...
            }
    }

    fun login() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            1000
        )
    }
}