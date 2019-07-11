package com.example.yizazun.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yizazun.data.entity.SpecialOffers

@Dao
interface SpecialOffersDao {

    @Query("SELECT * FROM special_offers WHERE id = :id")
    fun getSpecialOffersById(id:Int): LiveData<SpecialOffers>

    @Query("SELECT * FROM special_offers")
    fun getSpecialOffers(): LiveData<List<SpecialOffers>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSpecialOffers(specialOffers: SpecialOffers)

    @Update
    fun updateSpecialOffers(specialOffers: SpecialOffers)

    @Delete
    fun deleteSpecialOffers(specialOffers: SpecialOffers)
}