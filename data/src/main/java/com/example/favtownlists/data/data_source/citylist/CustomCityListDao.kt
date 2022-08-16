package com.example.favtownlists.data.data_source.citylist

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomCityListDao {

    @Query("SELECT * FROM custom_city_list WHERE name LIKE :name")
    suspend fun getCityList(name: String): CustomCityListEntity

    @Query("SELECT * FROM custom_city_list")
    fun getAllList(): Flow<List<CustomCityListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityList(entity: CustomCityListEntity)

    @Delete
    suspend fun delete(entity: CustomCityListEntity)
}