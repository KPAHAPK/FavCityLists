package com.example.favtownlists.screens.newlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.favtownlists.databinding.CityItemCheckboxedBinding
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.screens.main.CityDiffUtil
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class CityListCheckboxedAdapter(
    checkedCity: StateFlow<List<CityModel>>,
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    private val onCheckListener: OnCheckListener
) :
    RecyclerView.Adapter<CityListCheckboxedAdapter.CityCheckboxedVH>() {

    init {
        lifecycleCoroutineScope.launchWhenStarted {
            checkedCity.onEach {
                isCheckBoxEnabled = it.size <= MAX_CHECKBOX_SELECTION
                checkedItems = it.toMutableList()
            }.collect()
        }
    }

    var cityList: List<CityModel>
        get() = diff.currentList
        set(value) = diff.submitList(value)
    private var isCheckBoxEnabled = true
    private var checkedItems = mutableListOf<CityModel>()

    private val diffCallback = CityDiffUtil()
    val diff = AsyncListDiffer(this, diffCallback)

    interface OnCheckListener {
        fun onChecked(city: CityModel)
        fun onUnchecked(city: CityModel)
    }


    inner class CityCheckboxedVH(private val binding: CityItemCheckboxedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityModel) {
            binding.apply {
                checkbox.text = city.name
                val isChecked = checkedItems.contains(city)
                checkbox.isChecked = isChecked
                checkbox.setOnClickListener {
                    when (checkbox.isChecked) {
                        true -> {
                            if (isCheckBoxEnabled) {
                                onCheckListener.onChecked(city)
                            } else {
                                checkbox.isChecked = false
                            }
                        }
                        false -> {
                            onCheckListener.onUnchecked(city)
                        }
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityCheckboxedVH {
        val binding =
            CityItemCheckboxedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityCheckboxedVH(binding)
    }

    override fun onBindViewHolder(holder: CityCheckboxedVH, position: Int) {
        val city = diff.currentList[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    companion object {
        private const val MAX_CHECKBOX_SELECTION = 2
    }

}