package com.gg.petdoc_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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