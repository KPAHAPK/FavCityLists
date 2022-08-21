package com.example.favtownlists.screens.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.favtownlists.databinding.ListItemBinding
import com.example.favtownlists.repository.room.model.CityListInfoModel

class CityListsInfoAdapter : RecyclerView.Adapter<CityListsInfoAdapter.ListVH>() {

    var customCityListsInfo: List<CityListInfoModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

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

    val diffCallback = object : DiffUtil.ItemCallback<CityListInfoModel>() {
        override fun areItemsTheSame(
            oldItem: CityListInfoModel,
            newItem: CityListInfoModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CityListInfoModel,
            newItem: CityListInfoModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: ListVH, position: Int) {
        val cityListInfo = differ.currentList[position]
        holder.bind(cityListInfo)
    }

    override fun getItemCount(): Int {
        return customCityListsInfo.size
    }

}