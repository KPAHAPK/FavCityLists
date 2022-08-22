package com.example.favtownlists.screens.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.favtownlists.databinding.ListItemBinding
import com.example.favtownlists.repository.room.model.CityListInfoModel

class CityListsInfoAdapter : RecyclerView.Adapter<CityListsInfoAdapter.ListVH>() {

    var customCityListsInfo: List<CityListInfoModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val diffCallback = CityListInfoDiffUtil()
    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ListVH(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cityListInfo: CityListInfoModel) {
            binding.apply {
                listIcon.setColorFilter(cityListInfo.color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListVH(binding)
    }

    override fun onBindViewHolder(holder: ListVH, position: Int) {
        val cityListInfo = differ.currentList[position]
        holder.bind(cityListInfo)
    }

    override fun getItemCount(): Int {
        return customCityListsInfo.size
    }
}