package com.example.favtownlists.data.data_source.city

import androidx.room.*

@Entity(tableName = "cities")
data class CityEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "city")
        val city: String,
        @ColumnInfo(name = "foundingDate")
        val foundingDate: String
)
