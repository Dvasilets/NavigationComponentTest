package com.vasilets.navigationcomponenttest.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasilets.navigationcomponenttest.databinding.FragmentDashboardBinding
import com.vasilets.navigationcomponenttest.ui.dashboard.sampleRv.SampleAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var sampleAdapter: SampleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sampleAdapter = SampleAdapter(listOf(
            "Dashboard item 1",
            "Dashboard item 2",
            "Dashboard item 3",
            "Dashboard item 4",
            "Dashboard item 5",
            "Dashboard item 6",
            "Dashboard item 7",
        ))
        binding.sampleRv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.sampleRv.adapter = sampleAdapter
    }
    override fun onPause() {
        Log.d("M_Test", "Dashboard onPause")
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}