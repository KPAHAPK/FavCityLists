package com.example.favtownlists.screens.mylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.favtownlists.R

class CustomCityListsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_lists, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CustomCityListsFragment()
    }
}