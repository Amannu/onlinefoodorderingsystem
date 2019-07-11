package com.example.yizazun.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yizazun.data.entity.Drink

@Dao
interface DrinkDao {
    @Query("SELECT * FROM drink WHERE id = :id")
    fun getDrinkById(id:Int): LiveData<Drink>

    @Query("SELECT * FROM drink")
    fun getDrinks(): LiveData<List<Drink>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDrinks(drink: Drink)

    @Update
    fun updateDrinks(drink: Drink)

    @Delete
    fun deleteDrinks(drink: Drink)
}