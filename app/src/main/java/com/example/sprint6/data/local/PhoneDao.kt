package com.example.sprint6.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertsListProductsEntity(productsListEntitys: List<PhoneProductsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertsOneDetailsEntity(detailsEntitys: PhoneDetailsEntity)

    @Query("select * from product_table order by id ASC ")
    fun showListProductsEntity(): LiveData<List<PhoneProductsEntity>>

    @Query("select * from details_table where Id = :id ")
    fun ShowDetailsEntityForId(id: Int): LiveData<PhoneDetailsEntity>


}