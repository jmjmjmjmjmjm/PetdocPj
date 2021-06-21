package com.gg.petdoc_project.Frag


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.UiThread
import androidx.core.view.isGone
import androidx.viewpager2.widget.ViewPager2
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.gg.petdoc_project.*

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home_.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.log


class home_Frag() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as HomeActivity).topbar()
        return inflater.inflate(R.layout.fragment_home_, container, false)
    }

    override fun onStart() {
        super.onStart()

        login.setOnClickListener {
            activity?.let {
                val intent = Intent(context, InfoActivity::class.java)
                startActivity(intent)
            }
        }
        chat.setOnClickListener {
            activity?.let {
                val intent = Intent(context, ChatActivity::class.java)
                startActivity(intent)
            }
        }
    }
}