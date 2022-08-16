package com.example.favtownlists.data.data_source.city

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.favtownlists.repository.room.model.CityModel

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(city: List<CityEntity>)

    @Query("DELETE FROM cities")
    suspend fun deleteCity()

    @Query("SELECT * FROM cities")
    suspend fun getCities(): List<CityEntity>
}