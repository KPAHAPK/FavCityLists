package com.example.favtownlists.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.favtownlists.R
import com.example.favtownlists.databinding.FragmentCityListBinding
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.screens.main.adapter.CityListAdapter
import com.example.favtownlists.screens.main.adapter.ItemTouchCallBack

class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private val binding: FragmentCityListBinding by viewBinding()
    private lateinit var cityListAdapter: CityListAdapter
    private val itemTouchHelper by lazy {
        val itemTouchHelper = ItemTouchCallBack()
        ItemTouchHelper(itemTouchHelper)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
    }

    private fun setUpRv() {
        cityListAdapter = CityListAdapter()
        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(this@CityListFragment.context, LinearLayoutManager.VERTICAL, false)
            itemTouchHelper.attachToRecyclerView(this)
            adapter = cityListAdapter
            cityListAdapter.cityList = listOf(
                CityModel(null, "a","1"),
                CityModel(null, "b","2"),
                CityModel(null, "c","3"),
                CityModel(null, "d","4"),
                CityModel(null, "e","5"),
                CityModel(null, "f","6"),
            )
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CityListFragment()
    }
}