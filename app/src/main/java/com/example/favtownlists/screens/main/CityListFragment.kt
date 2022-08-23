package com.example.favtownlists.screens.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.favtownlists.R
import com.example.favtownlists.databinding.BottomSheetLayoutBinding
import com.example.favtownlists.databinding.FragmentCityListBinding
import com.example.favtownlists.screens.Screens
import com.example.favtownlists.screens.main.adapter.CenterZoomLayoutManager
import com.example.favtownlists.screens.main.adapter.CityListAdapter
import com.example.favtownlists.screens.main.adapter.CustomCityListsAdapter
import com.example.favtownlists.screens.main.adapter.ItemTouchCallBack
import com.example.favtownlists.utils.ScreenUtils
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private val binding: FragmentCityListBinding by viewBinding()
    private val viewModel: CityListViewModel by viewModels()

    @Inject
    lateinit var router: Router

    private lateinit var bottomSheet: BottomSheetLayoutBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var cityListAdapter: CityListAdapter
    private lateinit var cityListsInfoAdapter: CustomCityListsAdapter
    var initialListId: Int = 0
    private val itemTouchHelper by lazy {
        val itemTouchHelper = ItemTouchCallBack()
        ItemTouchHelper(itemTouchHelper)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomSheet()
        setObservers()
        initButton()
        setRecyclerView()
    }

    private fun initBottomSheet() {
        bottomSheet = binding.includedBottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet.container)


        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
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

        setViewPager()
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.customCityListsSF.collectLatest { customCityLists ->
                cityListsInfoAdapter.customCityLists = customCityLists
        }
        lifecycleScope.launchWhenStarted {
            viewModel.customCityListSF.collectLatest { customCityList ->
                with(customCityList) {

                }
            }
            }
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
                bottomSheet.rvLists.scrollToPosition(1)
            }
            listTab?.customView?.setOnClickListener {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                bottomSheet.rvLists.smoothScrollToPosition(1)
                listTab.select()
            }
        }
    }

    private fun setRecyclerView() {
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

    private fun setViewPager() {
        cityListsInfoAdapter = CustomCityListsAdapter(onItemClicked = { v ->
            binding.rvCity.apply {
                val position = getChildLayoutPosition(v)
                smoothScrollToPosition(position)
            }
        },
            onHeaderClicked = {
                router.navigateTo(Screens.NewListScreen())
            })
        cityListsInfoAdapter.customCityLists
        bottomSheet.rvLists.apply {
            adapter = cityListsInfoAdapter
            val centerZoomLayoutManager =
                CenterZoomLayoutManager(this@CityListFragment.requireContext())
            centerZoomLayoutManager.onItemSelectedListener =
                object : CenterZoomLayoutManager.OnItemSelectedListener {
                    override fun onItemSelected(position: Int) {
                        if (position == 0) {
                            smoothScrollToPosition(1)
                        } else {
                            val positionOffset = position - 1
                            val customCityList =
                                cityListsInfoAdapter.customCityLists[positionOffset]
                            bottomSheet.tvListName.text = customCityList.cityListInfo.name
                            cityListAdapter.cityList = customCityList.cities
                            val tab3 = binding.tabLayout.getTabAt(2)
                            val tab3Tittle =
                                tab3?.customView?.findViewById<TextView>(R.id.tab_name)
                            val tab3Image =
                                tab3?.customView?.findViewById<ImageView>(R.id.tab_icon)
                            tab3Tittle?.text = customCityList.cityListInfo.shortName
                            tab3Image?.setColorFilter(customCityList.cityListInfo.color)
                        }
                    }
                }
            layoutManager = centerZoomLayoutManager

            val padding: Int = ScreenUtils.getScreenWidth() / 2 - ScreenUtils.dpToPx(
                this@CityListFragment.requireContext(),
                52
            )
            setPadding(padding, 0, padding, 0)

            val linearSnapHelper = LinearSnapHelper()
            linearSnapHelper.attachToRecyclerView(this)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CityListFragment()
    }
}