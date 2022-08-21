package com.example.favtownlists.screens.main

import android.graphics.Color
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
import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.screens.main.adapter.CityListAdapter
import com.example.favtownlists.screens.main.adapter.CityListsInfoAdapter
import com.example.favtownlists.screens.main.adapter.ItemTouchCallBack
import com.example.favtownlists.screens.main.adapter.ZoomPageTransformer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private val binding: FragmentCityListBinding by viewBinding()
    private val viewModel: CityListViewModel by viewModels()

    private val bottomSheet by lazy { binding.includedBottomSheet.container }
    private val bottomSheetBehavior by lazy {
        BottomSheetBehavior.from(bottomSheet)
    }
    private lateinit var cityListAdapter: CityListAdapter
    private lateinit var cityListsInfoAdapter: CityListsInfoAdapter
    private val itemTouchHelper by lazy {
        val itemTouchHelper = ItemTouchCallBack()
        ItemTouchHelper(itemTouchHelper)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomSheet()
        setObserver()
        initButton()
        setRV()
        setVP()
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
            tab3Image?.setColorFilter(it.cityListInfo.color)
        }
    }

    private fun initButton() {
        binding.tabLayout.apply {
            val citiesTab = getTabAt(0)
            val defTab = getTabAt(1)
            val listTab = getTabAt(2)
            selectTab(defTab)
            defTab?.view?.isClickable = false
            listTab?.view?.isClickable = false
            citiesTab?.view?.setOnClickListener {
                viewModel.getCustomCityListById(1)
            }
            listTab?.customView?.setOnClickListener {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_COLLAPSED){
                            binding.background.visibility = View.GONE
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                       binding.background.apply {
                           visibility = View.VISIBLE
                           alpha = slideOffset
                       }
                    }

                })
                listTab.select()
            }
        }
    }

    private fun setRV() {
        cityListAdapter = CityListAdapter()
        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(
                this@CityListFragment.requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            itemTouchHelper.attachToRecyclerView(this)
            adapter = cityListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@CityListFragment.requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

    }

    private fun setVP() {
        cityListsInfoAdapter = CityListsInfoAdapter()
        val list = listOf(
            CityListInfoModel(1, "aaaaa", "a", Color.GREEN),
            CityListInfoModel(2, "bbbbb", "b", Color.BLUE),
            CityListInfoModel(3, "ccccc", "c", Color.CYAN),
            CityListInfoModel(4, "ddddd", "d", Color.RED),
            CityListInfoModel(5, "eeeee", "e", Color.YELLOW),
            CityListInfoModel(6, "fffff", "f", Color.MAGENTA),
        )
        binding.includedBottomSheet.vpLists.apply {
            offscreenPageLimit = 1
            setClipToPadding(false);
            setPadding(300, 0, 300, 0);
            setPageTransformer(ZoomPageTransformer())
//            }
            adapter = cityListsInfoAdapter
            cityListsInfoAdapter.customCityListsInfo = list
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CityListFragment()
    }
}