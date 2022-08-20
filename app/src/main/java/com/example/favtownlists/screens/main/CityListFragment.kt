package com.example.favtownlists.screens.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.favtownlists.R
import com.example.favtownlists.databinding.FragmentCityListBinding
import com.example.favtownlists.screens.main.adapter.CityListAdapter
import com.example.favtownlists.screens.main.adapter.ItemTouchCallBack
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private val binding: FragmentCityListBinding by viewBinding()
    private val viewModel: CityListViewModel by viewModels()

    private val bottomSheet by lazy { binding.includedBottomSheet.bottomSheetContainer }
    private val bottomSheetBehavior by lazy {
        BottomSheetBehavior.from(bottomSheet)
    }
    private lateinit var cityListAdapter: CityListAdapter
    private val itemTouchHelper by lazy {
        val itemTouchHelper = ItemTouchCallBack()
        ItemTouchHelper(itemTouchHelper)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomSheet()
        setObserver()
        initButton()
        setUpRv()
    }

    private fun initBottomSheet() {

    }

    private fun setObserver() {
        viewModel.customCityListLD.observe(viewLifecycleOwner) {
            cityListAdapter.cityList = it.cities
            val tab3 = binding.tabLayout.getTabAt(2)
            val tab3Tittle = tab3?.customView?.findViewById<TextView>(R.id.tab_name)
            val tab3Image = tab3?.customView?.findViewById<ImageView>(R.id.tab_icon)
            tab3Tittle?.text = it.cityListInfo.shortName
            tab3Image?.setColorFilter(it.cityListInfo.color,)
        }
    }

    private fun initButton() {
        binding.tabLayout.apply {
            val defTab = getTabAt(1)
            val listTab = getTabAt(2)
            selectTab(defTab)
            defTab?.view?.isClickable = false
            listTab?.view?.isClickable = false
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            viewModel.getCustomCityListById(1)
                            getTabAt(2)?.view?.isClickable = true
                        }
                        2 -> {
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                        }

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }
    }

    private fun setUpRv() {
        cityListAdapter = CityListAdapter()
        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(
                this@CityListFragment.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            itemTouchHelper.attachToRecyclerView(this)
            adapter = cityListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@CityListFragment.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CityListFragment()
    }
}