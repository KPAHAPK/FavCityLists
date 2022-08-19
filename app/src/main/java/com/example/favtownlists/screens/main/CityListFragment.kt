package com.example.favtownlists.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.favtownlists.R
import com.example.favtownlists.databinding.FragmentCityListBinding
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.repository.room.model.CustomCityListModel
import com.example.favtownlists.screens.main.adapter.CityListAdapter
import com.example.favtownlists.screens.main.adapter.ItemTouchCallBack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private val binding: FragmentCityListBinding by viewBinding()
    private val viewModel: CityListViewModel by viewModels()
    private lateinit var cityListAdapter: CityListAdapter
    private val itemTouchHelper by lazy {
        val itemTouchHelper = ItemTouchCallBack()
        ItemTouchHelper(itemTouchHelper)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
        initButton()
    }

    private fun initButton() {
        binding.btnShowCities.setOnClickListener {
            viewModel.getCustomCityListById(1)
        }
    }

    private fun setUpRv() {
        cityListAdapter = CityListAdapter()
        lifecycleScope.launchWhenStarted {
            viewModel.customCityList
                .onEach {
                    val cityList = it?.cities ?: listOf()
                    cityListAdapter.cityList = cityList
                }
                .collect()
        }
        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(
                this@CityListFragment.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            itemTouchHelper.attachToRecyclerView(this)
            adapter = cityListAdapter
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CityListFragment()
    }
}