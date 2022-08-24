package com.example.favcitylists.screens.newlist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.favcitylists.R
import com.example.favcitylists.databinding.FragmentNewListBinding
import com.example.favcitylists.repository.room.model.CityModel
import com.example.favcitylists.screens.newlist.adapter.CityListCheckboxedAdapter
import com.example.favcitylists.utils.listOfColors
import com.github.terrakok.cicerone.Router
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class NewCustomCityListFragment : Fragment(R.layout.fragment_new_list) {

    private val binding: FragmentNewListBinding by viewBinding()
    private val viewModel: NewCustomCityListViewModel by viewModels()

    @Inject
    lateinit var router: Router

    private lateinit var citiListAdapter: CityListCheckboxedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
        initButtons()
        setObservers()
        setRecyclerView()
    }

    private fun initSpinner() {
        val spinnerItems = listOfColors.map { getString(it.colorName) }
        val spinnerAdapter = ArrayAdapter(
            this@NewCustomCityListFragment.requireContext(),
            android.R.layout.simple_spinner_item,
            spinnerItems
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerColor.apply {
            adapter = spinnerAdapter
            setSelection(0, false)
        }
    }

    private fun initButtons() {
        binding.btnCancle.setOnClickListener {
            router.exit()
        }
        binding.btnConfirm.setOnClickListener {
            val spinnerPosition = binding.spinnerColor.selectedItemPosition
            viewModel.updateCityListModel(
                viewModel.cityListInfoSF.value.copy(
                    name = binding.etName.text.toString(),
                    shortName = binding.etShortName.text.toString(),
                    color = ContextCompat.getColor(
                        this@NewCustomCityListFragment.requireContext(),
                        listOfColors[spinnerPosition].color
                    )
                )
            )
            viewModel.insertToDatabase()
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.cityListSF.collectLatest { list ->
                citiListAdapter.cityList = list
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    NewCustomCityListViewModel.UIEvent.SaveCustomCityList -> {
                        router.exit()
                    }
                    is NewCustomCityListViewModel.UIEvent.ShowSnackBar -> {
                        val snackbar =
                            Snackbar.make(
                                this@NewCustomCityListFragment.requireContext(),
                                binding.root,
                                event.message,
                                Snackbar.LENGTH_SHORT
                            )
                        snackbar.show()
                    }
                }
            }
        }
    }

    private fun setRecyclerView() {
        citiListAdapter = CityListCheckboxedAdapter(
            viewModel.checkedCitySF,
            lifecycleScope,
            object : CityListCheckboxedAdapter.OnCheckListener {
                override fun onChecked(city: CityModel) {
                    viewModel.addCheckedPosition(city)
                }

                override fun onUnchecked(city: CityModel) {
                    viewModel.removeCheckedCity(city)
                }

            })
        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(
                this@NewCustomCityListFragment.requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = citiListAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewCustomCityListFragment()

    }
}