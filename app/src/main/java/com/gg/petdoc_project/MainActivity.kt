package com.gg.petdoc_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.fragment_home_.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread(Runnable {
            Thread.sleep(1000)
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            finish()
            startActivity(intent)
        }).start()
    }

}