package com.example.favtownlists.data.data_source

import androidx.room.*
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.citylist.CityListInfoEntity
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRefEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanchOfCities(city: List<CityEntity>)

    @Insert(entity = CityListInfoEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityListInfo(CityListInfo: CityListInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanchOfCityListInfo(CityListInfos: List<CityListInfoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: CustomListCrossRefEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanchOfCrossRefs(crossRefs: List<CustomListCrossRefEntity>)


    @Query("SELECT * FROM cities WHERE name = :name")
    suspend fun getCity(name: String): CityEntity

    @Query("SELECT * FROM cities")
    fun getBanchOfCities(): Flow<List<CityEntity>>

    @Query("SELECT * FROM city_list_info WHERE name = :name")
    suspend fun getCityListInfo(name: String): CityListInfoEntity

    @Query("SELECT * FROM city_list_info")
    fun getBanchOfCityListInfo(): Flow<List<CityListInfoEntity>>

    @Transaction
    @Query("SELECT * FROM city_list_info WHERE name LIKE :name")
    suspend fun getCustomList(name: String): CustomCityList

    @Transaction
    @Query("SELECT * FROM city_list_info")
    fun getBanchOfCustomLists(): Flow<List<CustomCityList>>


    @Delete
    suspend fun deleteCity(city: CityEntity)

    @Delete
    suspend fun deleteBanchOfCities(cities: List<CityEntity>)

    @Delete
    suspend fun deleteCityList(cityListEntity: CityListInfoEntity)

    @Delete
    suspend fun deleteBanchOfCityLists(cityListEntity: List<CityListInfoEntity>)

    @Delete
    suspend fun deleteCrossRef(crossRef: CustomListCrossRefEntity)

    @Transaction
    @Query("DELETE FROM custom_list_cross_ref WHERE city_list_info_id = :customListId")
    suspend fun deleteCustomListById(customListId: Int)

    @Transaction
    @Query("DELETE FROM custom_list_cross_ref WHERE city_list_info_id = :customListId AND city_id = :cityId")
    suspend fun deleteCityFromCustomList(customListId: Int, cityId: Int)
}