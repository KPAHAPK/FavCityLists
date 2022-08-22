package com.example.favtownlists.screens.main

import androidx.recyclerview.widget.DiffUtil
import com.example.favtownlists.repository.room.model.CityModel

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