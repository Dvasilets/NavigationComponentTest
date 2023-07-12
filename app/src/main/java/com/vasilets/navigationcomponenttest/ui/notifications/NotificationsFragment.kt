package com.vasilets.navigationcomponenttest.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasilets.navigationcomponenttest.R
import com.vasilets.navigationcomponenttest.databinding.FragmentNotificationsBinding
import com.vasilets.navigationcomponenttest.ui.dashboard.sampleRv.SampleAdapter

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notifsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_notifs_to_bs)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("M_TestLifecycle", "Notifications onCreate")
    }
    override fun onResume() {
        super.onResume()
        Log.d("M_TestLifecycle", "Notifications onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("M_TestLifecycle", "Notifications onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_TestLifecycle", "Notifications onDestroy")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}