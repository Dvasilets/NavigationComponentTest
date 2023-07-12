package com.vasilets.navigationcomponenttest.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vasilets.navigationcomponenttest.R
import com.vasilets.navigationcomponenttest.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var demoCollectionAdapter: HomeAdapter? = null
    private var viewPager: ViewPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        demoCollectionAdapter = HomeAdapter(this)
        viewPager = binding.pager
        viewPager?.adapter = demoCollectionAdapter
        val tabLayout = binding.shortTabLayout
        TabLayoutMediator(tabLayout, viewPager!!) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("M_TestLifecycle", "Home onCreate")
    }
    override fun onResume() {
        super.onResume()
        Log.d("M_TestLifecycle", "Home onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("M_TestLifecycle", "Home onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_TestLifecycle", "Home onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}