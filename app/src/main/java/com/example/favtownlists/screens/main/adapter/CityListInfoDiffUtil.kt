package com.example.favtownlists.screens.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.favtownlists.repository.room.model.CityListInfoModel

class CityListInfoDiffUtil : DiffUtil.ItemCallback<CityListInfoModel>() {
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