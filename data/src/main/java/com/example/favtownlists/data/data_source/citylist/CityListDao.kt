package com.example.favtownlists.data.data_source.citylist

import androidx.room.*

@Dao
interface CityListDao {

    @Query("SELECT * FROM citylist WHERE name LIKE :name")
    suspend fun getCityList(name: String): CityListEntity

    @Query("SELECT * FROM citylist")
    suspend fun getAllList(): List<CityListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityList(entity: CityListEntity)

    @Delete
    suspend fun delete(entity: CityListEntity)
}