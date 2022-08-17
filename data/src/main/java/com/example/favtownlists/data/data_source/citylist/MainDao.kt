package com.example.favtownlists.data.data_source.citylist

import androidx.room.*
import com.example.favtownlists.data.data_source.CustomList
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(city: List<CityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityListInfo(CityListInfo: CityListInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanchOfCityListInfos(CityListInfos: List<CityListInfoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: CustomListCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRefs(crossRefs: List<CustomListCrossRef>)

    @Query("SELECT * FROM cities")
    suspend fun getCities(): List<CityEntity>

    @Query("SELECT * FROM cities WHERE name = :name")
    suspend fun getCity(name: CityEntity): CityEntity

    @Query("SELECT * FROM city_list_info WHERE name = :name")
    suspend fun getCityListInfo(name: String): CityListInfoEntity

    @Query("SELECT * FROM city_list_info")
    suspend fun getAllCityListInfos(): List<CityListInfoEntity>

    @Transaction
    @Query("SELECT * FROM city_list_info WHERE name LIKE :name")
    suspend fun getCustomList(name: String): CustomList

    @Transaction
    @Query("SELECT * FROM city_list_info")
    suspend fun getCustomLists(): List<CustomList>

//    @Transaction
//    suspend fun insertCustomListWithCities(customList: CustomList, cities: List<CityEntity>){
//        val listId = insertCityListInfo()
//    }

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

    @Query("DELETE FROM custom_list_cross_ref WHERE city_list_info_id = :customListId")
    suspend fun deleteCustomListById(customListId: Int)

    @Query("DELETE FROM custom_list_cross_ref WHERE city_list_info_id = :customListId AND city_id = :cityId")
    suspend fun deleteCityFromCustomList(customListId: Int, cityId: Int)
}