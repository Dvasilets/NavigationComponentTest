package com.vasilets.navigationcomponenttest.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vasilets.navigationcomponenttest.ui.home.pagerFragments.FirstFragment

class HomeAdapter(
    f: Fragment
): FragmentStateAdapter(f) {

    companion object {
        private const val SHORTS_TYPE_SIZE = 2
    }

    override fun getItemCount(): Int {
        return SHORTS_TYPE_SIZE
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FirstFragment()
        fragment.arguments = Bundle().apply {
            putInt("param1", position + 1)
        }
        return fragment
    }
}