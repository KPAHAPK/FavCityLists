package com.example.favcitylists.data.data_source.city

import androidx.room.*

@Entity(tableName = "cities")
data class CityEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "city_id")
        val id: Int? = null,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "founding_date")
        val foundingDate: String
)
