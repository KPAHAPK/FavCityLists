package com.example.favtownlists.data.data_source.crossref

import androidx.room.Entity

@Entity(primaryKeys = ["cityId", "customCityListId"])
data class CustomListCrossRef(
    val cityId: Int,
    val customCityListId: Int,
)
