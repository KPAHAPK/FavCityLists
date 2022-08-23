package com.example.favcitylists.screens.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.favcitylists.repository.room.model.CityModel

class CityDiffUtil : DiffUtil.ItemCallback<CityModel>() {
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