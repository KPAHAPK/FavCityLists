package com.example.favtownlists.data.data_source

import androidx.room.*
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.citylist.CityListInfoEntity
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(city: List<CityEntity>)

    @Insert(entity = CityListInfoEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityListInfo(CityListInfo: CityListInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanchOfCityListInfos(CityListInfos: List<CityListInfoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: CustomListCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRefs(crossRefs: List<CustomListCrossRef>)

    @Query("SELECT * FROM cities WHERE name = :name")
    fun getCity(name: String): Flow<CityEntity>

    @Query("SELECT * FROM cities")
    fun getCities(): Flow<List<CityEntity>>

    @Query("SELECT * FROM city_list_info WHERE name = :name")
    fun getCityListInfo(name: String): Flow<CityListInfoEntity>

    @Query("SELECT * FROM city_list_info")
    fun getAllCityListInfos(): Flow<List<CityListInfoEntity>>

    @Transaction
    @Query("SELECT * FROM city_list_info WHERE name LIKE :name")
    fun getCustomList(name: String): Flow<CustomList>

    @Transaction
    @Query("SELECT * FROM city_list_info")
    fun getCustomLists(): Flow<List<CustomList>>

    @Delete
    suspend fun deleteCity(city: CityEntity)

    @Delete
    suspend fun deleteCities(cities: List<CityEntity>)
    
    @Delete
    suspend fun deleteCityList(cityListEntity: CityListInfoEntity)
    
    @Delete
    suspend fun deleteBanchOfCityLists(cityListEntity: List<CityListInfoEntity>)

    @Delete
    suspend fun deleteCrossRef(crossRef: CustomListCrossRef)

    @Transaction
    @Query("DELETE FROM custom_list_cross_ref WHERE city_list_info_id = :customListId")
    suspend fun deleteCustomListById(customListId: Int)

    @Transaction
    @Query("DELETE FROM custom_list_cross_ref WHERE city_list_info_id = :customListId AND city_id = :cityId")
    suspend fun deleteCityFromCustomList(customListId: Int, cityId: Int)
}