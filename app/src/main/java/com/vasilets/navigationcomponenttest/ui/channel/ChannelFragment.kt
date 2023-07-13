package com.vasilets.navigationcomponenttest.ui.channel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.vasilets.navigationcomponenttest.R
import com.vasilets.navigationcomponenttest.databinding.FragmentChannelBinding

class ChannelFragment : Fragment() {

    private var _binding: FragmentChannelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val channelViewModel =
            ViewModelProvider(this).get(ChannelViewModel::class.java)

        _binding = FragmentChannelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        channelViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.channelBtn.setOnClickListener {
            //val bundle = bundleOf("overlay" to true)
            findNavController().navigate(R.id.action_channel_to_overlayFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("M_TestLifecycle", "Channel onCreate")
    }
    override fun onResume() {
        super.onResume()
        Log.d("M_TestLifecycle", "Channel onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("M_TestLifecycle", "Channel onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_TestLifecycle", "Channel onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}