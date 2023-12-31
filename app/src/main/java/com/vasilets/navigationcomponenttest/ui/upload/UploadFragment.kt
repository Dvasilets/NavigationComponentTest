package com.vasilets.navigationcomponenttest.ui.upload

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vasilets.navigationcomponenttest.databinding.FragmentUploadBinding

class UploadFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentUploadBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val uploadViewModel =
            ViewModelProvider(this).get(UploadViewModel::class.java)

        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        uploadViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("M_TestLifecycle", "Upload onCreate")
    }
    override fun onResume() {
        super.onResume()
        Log.d("M_TestLifecycle", "Upload onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("M_TestLifecycle", "Upload onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_TestLifecycle", "Upload onDestroy")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}