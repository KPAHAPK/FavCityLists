package com.example.favtownlists.screens.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.favtownlists.databinding.CityItemBinding
import com.example.favtownlists.repository.room.model.CityModel

class CityListAdapter : RecyclerView.Adapter<CityListAdapter.CityVH>() {

    var cityList : List<CityModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    inner class CityVH(private val binding: CityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityModel) {
            binding.apply {
                tvName.text = city.name
                tvFoundingDate.text = city.foundingDate
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<CityModel>() {
        override fun areItemsTheSame(
            oldItem: CityModel,
            newItem: CityModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CityModel,
            newItem: CityModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityVH {
        val binding =
            CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityVH(binding)
    }

    override fun onBindViewHolder(holder: CityVH, position: Int) {
        val cityModel = differ.currentList[position]
        holder.bind(cityModel)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}