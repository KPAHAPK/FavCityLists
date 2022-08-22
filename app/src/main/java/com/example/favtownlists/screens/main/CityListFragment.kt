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
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.favtownlists.R
import com.example.favtownlists.databinding.BottomSheetLayoutBinding
import com.example.favtownlists.databinding.FragmentCityListBinding
import com.example.favtownlists.screens.Screens
import com.example.favtownlists.screens.main.adapter.CityListAdapter
import com.example.favtownlists.screens.main.adapter.CityListsInfoAdapter
import com.example.favtownlists.screens.main.adapter.ItemTouchCallBack
import com.example.favtownlists.screens.main.adapter.ZoomPageTransformer
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
    private lateinit var cityListsInfoAdapter: CityListsInfoAdapter
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

        bottomSheet.btnAdd.setOnClickListener {
            router.navigateTo(Screens.NewListScreen())
        }
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
//        lifecycleScope.launchWhenStarted {
//            viewModel.customCityListSF.collectLatest {
//
//            }
//        }
        lifecycleScope.launchWhenStarted {
            viewModel.customCityListsSF.collectLatest { customCityList ->
                cityListsInfoAdapter.customCityListsInfo = customCityList.map { it.cityListInfo }
                with(customCityList[0]){
                    cityListAdapter.cityList = this.cities
                    val tab3 = binding.tabLayout.getTabAt(2)
                    val tab3Tittle = tab3?.customView?.findViewById<TextView>(R.id.tab_name)
                    val tab3Image = tab3?.customView?.findViewById<ImageView>(R.id.tab_icon)
                    tab3Tittle?.text = this.cityListInfo.shortName
                    tab3Image?.setColorFilter(this.cityListInfo.color)
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
                    viewModel.getCustomCityLists()
                }
                listTab?.customView?.setOnClickListener {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
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
            cityListsInfoAdapter = CityListsInfoAdapter()
            bottomSheet.vpLists.apply {
                offscreenPageLimit = 1
                setClipToPadding(false);
                setPadding(300, 0, 300, 0);
                setPageTransformer(ZoomPageTransformer())
                adapter = cityListsInfoAdapter
            }
        }

        companion object {
            @JvmStatic
            fun newInstance() = CityListFragment()
        }
    }