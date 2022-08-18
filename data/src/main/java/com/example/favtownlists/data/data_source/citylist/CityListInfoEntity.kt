package com.example.favtownlists.data.data_source.citylist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_list_info")
data class CityListInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_list_info_id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String
)

