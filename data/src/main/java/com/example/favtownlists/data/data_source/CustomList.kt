package com.example.favtownlists.data.data_source

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.citylist.CityListInfoEntity
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRef

data class CustomList(
    @Embedded
    val customCityList: CityListInfoEntity,
    @Relation(
        parentColumn = "city_list_info_id",
        entity = CityEntity::class,
        entityColumn = "city_id",
        associateBy = Junction(CustomListCrossRef::class,
        parentColumn = "city_list_info_id",
        entityColumn = "city_id")
    )
    val cities: List<CityEntity>
)
