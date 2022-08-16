package com.example.favtownlists.data.data_source.citylist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.favtownlists.repository.room.model.CitiesInList

@Entity(tableName = "citylist")
data class CustomCityListEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "cities")
    @field:TypeConverters(CustomCityListConverter::class)
    val cities: CitiesInList
)

