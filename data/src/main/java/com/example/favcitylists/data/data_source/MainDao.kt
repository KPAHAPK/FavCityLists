package com.example.favcitylists.data.data_source

import androidx.room.*
import com.example.favcitylists.data.data_source.city.CityEntity
import com.example.favcitylists.data.data_source.citylist.CityListInfoEntity
import com.example.favcitylists.data.data_source.crossref.CustomListCrossRefEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanchOfCities(city: List<CityEntity>)

    @Insert(entity = CityListInfoEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityListInfo(CityListInfo: CityListInfoEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanchOfCityListInfo(CityListInfos: List<CityListInfoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: CustomListCrossRefEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanchOfCrossRefs(crossRefs: List<CustomListCrossRefEntity>)

    @Query("SELECT * FROM cities WHERE city_id = :id")
    suspend fun getCityById(id: Int): CityEntity

    @Query("SELECT * FROM cities")
    suspend fun getAllCities(): List<CityEntity>

    @Query("SELECT * FROM city_list_info WHERE city_list_info_id = :id")
    suspend fun getCityListInfoById(id: Int): CityListInfoEntity

    @Query("SELECT * FROM city_list_info WHERE name = :name")
    suspend fun getCityListInfoByName(name: String): CityListInfoEntity

    @Query("SELECT * FROM city_list_info")
    suspend fun getAllCityListInfo(): List<CityListInfoEntity>

    @Transaction
    @Query("SELECT * FROM city_list_info WHERE city_list_info_id = :id")
    fun getCustomListById(id: Int): Flow<CustomCityList>

    @Transaction
    @Query("SELECT * FROM city_list_info")
    fun getAllCustomLists(): Flow<List<CustomCityList>>


    @Delete
    suspend fun deleteCity(city: CityEntity)

    @Delete
    suspend fun deleteAllCities(cities: List<CityEntity>)

    @Delete
    suspend fun deleteCityList(cityListEntity: CityListInfoEntity)

    @Delete
    suspend fun deleteAllCityLists(cityListEntity: List<CityListInfoEntity>)

    @Delete
    suspend fun deleteCrossRef(crossRef: CustomListCrossRefEntity)

    @Transaction
    @Query("DELETE FROM custom_list_cross_ref WHERE city_list_info_id = :customListId")
    suspend fun deleteCustomListById(customListId: Int)

    @Transaction
    @Query("DELETE FROM custom_list_cross_ref WHERE city_list_info_id = :customListId AND city_id = :cityId")
    suspend fun deleteCityFromCustomList(customListId: Int, cityId: Int)
}