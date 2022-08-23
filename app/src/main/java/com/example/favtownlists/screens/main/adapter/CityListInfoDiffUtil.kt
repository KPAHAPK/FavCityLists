package com.example.favtownlists.screens.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.repository.room.model.CustomCityListModel

class CityListInfoDiffUtil : DiffUtil.ItemCallback<CustomCityListModel>() {
    override fun areItemsTheSame(
        oldItem: CustomCityListModel,
        newItem: CustomCityListModel
    ): Boolean {
        return oldItem.cityListInfo.id == newItem.cityListInfo.id
    }

    override fun areContentsTheSame(
        oldItem: CustomCityListModel,
        newItem: CustomCityListModel
    ): Boolean {
        return oldItem == newItem
    }
}