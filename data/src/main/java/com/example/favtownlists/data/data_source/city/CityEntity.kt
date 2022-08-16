package com.example.favtownlists.data.data_source.city

import androidx.room.*

@Entity(tableName = "cities")
data class CityEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int? = null,
        @ColumnInfo(name = "city_owner_id")
        val customCityListId: Int? = null,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "foundingDate")
        val foundingDate: String
)
