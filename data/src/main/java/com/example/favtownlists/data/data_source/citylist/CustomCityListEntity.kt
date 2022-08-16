package com.example.favtownlists.data.data_source.citylist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "custom_city_list")
data class CustomCityListEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "custom_city_list_id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String
)

