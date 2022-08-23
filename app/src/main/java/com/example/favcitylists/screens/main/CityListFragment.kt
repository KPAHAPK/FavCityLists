package com.example.favcitylists.screens.main

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
import com.example.favcitylists.R
import com.example.favcitylists.databinding.BottomSheetLayoutBinding
import com.example.favcitylists.databinding.FragmentCityListBinding
import com.example.favcitylists.screens.Screens
import com.example.favcitylists.screens.main.adapter.CenterZoomLayoutManager
import com.example.favcitylists.screens.main.adapter.CityListAdapter
import com.example.favcitylists.screens.main.adapter.CustomCityListsAdapter
import com.example.favcitylists.screens.main.adapter.ItemTouchCallBack
import com.example.favcitylists.utils.ScreenUtils
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
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
    var initialIndex: Int = 0
    private val itemTouchHelper by lazy {
        val itemTouchHelper = ItemTouchCallBack()
        ItemTouchHelper(itemTouchHelper)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomSheet()
        setObservers()
        initButton()
        setRecyclerViewCities()
    }


    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.customCityListsSF.collectLatest { customCityLists ->
                cityListsInfoAdapter.customCityLists = customCityLists.map { it.cityListInfo }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.customCityListSF.onEach { customCityList ->
                customCityList?.let { list ->
                    cityListAdapter.cityList = list.cities
                    val tab3 = binding.tabLayout.getTabAt(2)
                    val tab3Tittle =
                        tab3?.customView?.findViewById<TextView>(R.id.tab_name)
                    val tab3Image =
                        tab3?.customView?.findViewById<ImageView>(R.id.tab_icon)
                    tab3Tittle?.text = list.cityListInfo.shortName
                    tab3Image?.setColorFilter(list.cityListInfo.color)
                    bottomSheet.tvListName.text = list.cityListInfo.name
                    cityListAdapter.cityList = list.cities
                }
            }.collect()
        }
        lifecycleScope.launchWhenStarted {
            viewModel.bottomSheetIsActive.collectLatest { isActive ->
                if (isActive) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                    bottomSheet.rvLists.scrollToPosition(initialIndex + 1)
                    bottomSheet.rvLists.smoothScrollToPosition(initialIndex + 1)
                } else {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
        }
    }


    private fun initBottomSheet() {
        bottomSheet = binding.includedBottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet.container)
        //Включаем и отключаем затемнение позади BottomSheet
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    binding.background.visibility = View.GONE
                    viewModel.setBottomSheetActive(false)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.background.apply {
                    visibility = View.VISIBLE
                    alpha = slideOffset
                }
            }
        })
        setRecyclerViewCityInfo()
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
                viewModel.setCustomCityList(initialIndex)
            }
            listTab?.customView?.setOnClickListener {
                viewModel.setBottomSheetActive(true)
                listTab.select()
            }
        }
    }

    private fun setRecyclerViewCities() {
        cityListAdapter = CityListAdapter()
        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(
                this@CityListFragment.requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            //Устанавливаем возможность перетаскивать элементы
            itemTouchHelper.attachToRecyclerView(this)
            adapter = cityListAdapter
            //Разделитель элементов
            addItemDecoration(
                DividerItemDecoration(
                    this@CityListFragment.requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

    }

    private fun setRecyclerViewCityInfo() {
        cityListsInfoAdapter = CustomCityListsAdapter { v, viewType ->
            //Обработка клика по кнопки добавления списка
            when (viewType) {
                CustomCityListsAdapter.TYPE_HEADER -> {
                    viewModel.setBottomSheetActive(false)
                    router.navigateTo(Screens.NewListScreen())
                }
            }
        }
        bottomSheet.rvLists.apply {
            adapter = cityListsInfoAdapter
            //Зумирование и callback элементов при скролле списка
            val centerZoomLayoutManager =
                CenterZoomLayoutManager(this@CityListFragment.requireContext())
            centerZoomLayoutManager.onItemSelectedListener =
                object : CenterZoomLayoutManager.OnItemSelectedListener {
                    override fun onItemSelected(position: Int) {
                        if (position == 0) {
                            smoothScrollToPosition(1)
                        } else {
                            val positionOffset = position - 1
                            initialIndex = positionOffset
                            viewModel.setCustomCityList(initialIndex)
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