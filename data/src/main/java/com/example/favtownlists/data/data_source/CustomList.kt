package com.example.favtownlists.data.data_source

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.citylist.CustomCityListEntity
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRef

data class CustomList(
    @Embedded
    val customCityList: CustomCityListEntity,
    @Relation(
        parentColumn = "custom_city_list_id",
        entity = CityEntity::class,
        entityColumn = "city_owner_id"
    )
    val cities: List<CityEntity>
)
