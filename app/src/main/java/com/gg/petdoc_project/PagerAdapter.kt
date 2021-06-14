package com.gg.petdoc_project

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gg.petdoc_project.Frag.*

open class PagerAdapter(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return home_Frag()
            }
            1 -> {
                return map_Frag()
            }
            else -> {
                return shop_Frag()
            }

        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}