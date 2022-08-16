package com.example.favtownlists.data.data_source.citylist

import androidx.room.*

@Dao
interface CustomCityListDao {

    @Query("SELECT * FROM citylist WHERE name LIKE :name")
    suspend fun getCityList(name: String): CustomCityListEntity

    @Query("SELECT * FROM citylist")
    suspend fun getAllList(): List<CustomCityListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityList(entity: CustomCityListEntity)

    @Delete
    suspend fun delete(entity: CustomCityListEntity)
}