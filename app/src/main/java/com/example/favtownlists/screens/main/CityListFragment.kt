package com.example.favtownlists.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.favtownlists.R
import com.example.favtownlists.databinding.FragmentCityListBinding
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.screens.main.adapter.CityListAdapter
import com.example.favtownlists.screens.main.adapter.ItemTouchCallBack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
        viewModel.insert()
    }

    private fun setUpRv() {

        cityListAdapter = CityListAdapter()
        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(this@CityListFragment.context, LinearLayoutManager.VERTICAL, false)
            itemTouchHelper.attachToRecyclerView(this)
            adapter = cityListAdapter
            lifecycleScope.launchWhenStarted {
                viewModel.allCities
                    .onEach {
                        cityListAdapter.cityList = it
                    }
                    .collect()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CityListFragment()
    }
}