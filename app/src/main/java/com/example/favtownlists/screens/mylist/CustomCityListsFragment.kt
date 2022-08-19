package com.example.favtownlists.screens.mylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.favtownlists.R
import com.example.favtownlists.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomCityListsFragment : BottomSheetDialogFragment() {

    val binding: BottomSheetLayoutBinding by viewBinding()

    companion object {
        @JvmStatic
        fun newInstance() = CustomCityListsFragment()
    }
}