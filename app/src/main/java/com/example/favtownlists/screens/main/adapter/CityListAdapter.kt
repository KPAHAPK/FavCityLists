package com.example.favtownlists.screens.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favtownlists.databinding.CityItemBinding
import com.example.favtownlists.repository.room.model.CityModel

class CityListAdapter : RecyclerView.Adapter<CityListAdapter.CityVH>() {

    var cityList = listOf<CityModel>()

    inner class CityVH(private val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityModel) {
            binding.apply {
                tvName.text = city.name
                tvFoundingDate.text = city.foundingDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityVH {
        val binding =
            CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityVH(binding)
    }

    override fun onBindViewHolder(holder: CityVH, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}