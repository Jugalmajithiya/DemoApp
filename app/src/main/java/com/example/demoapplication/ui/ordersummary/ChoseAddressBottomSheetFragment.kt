package com.example.demoapplication.ui.ordersummary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.demoapplication.databinding.FragmentChoseAddressBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ChoseAddressBottomSheetFragment : BottomSheetDialogFragment() {
  lateinit var binding : FragmentChoseAddressBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChoseAddressBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ctvAddress.setOnClickListener {
            binding.ctvAddress.isChecked = !binding.ctvAddress.isChecked
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (ex: Exception) {
            Log.e("BaseBottomSheetFragment", "Exception", ex)
        }
    }
}