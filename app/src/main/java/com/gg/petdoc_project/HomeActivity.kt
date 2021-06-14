package com.gg.petdoc_project

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gg.petdoc_project.Frag.ReAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_shop_.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class HomeActivity : AppCompatActivity() {
    lateinit var adapter: ReAdapter
    var re: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom()
        val pagerAdapter = PagerAdapter(supportFragmentManager, 4)
        view_pager.adapter = pagerAdapter

    }

    private fun getlist(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.home_view1, R.drawable.home_view2, R.drawable.home_view3)
    }


    fun topbar() {
        Handler().postDelayed({
            var adapter = Home_Adapter(getlist())
            var a = findViewById<ViewPager2>(R.id.home_viewpager)
            a.adapter = adapter
        }, 1000)
    }

    fun bottom() {
        tab_layout.addTab(tab_layout.newTab().setText("홈"))
        tab_layout.addTab(tab_layout.newTab().setText("병원예약"))
        tab_layout.addTab(tab_layout.newTab().setText("게시글"))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position
            }
        })
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
    }

    fun read(context: Context?, listdata: ArrayList<BoardDto>, re:RecyclerView) {
        adapter = ReAdapter(listdata, LayoutInflater.from(this))
        re.adapter = adapter
        re.layoutManager = LinearLayoutManager(this)
    }

}
