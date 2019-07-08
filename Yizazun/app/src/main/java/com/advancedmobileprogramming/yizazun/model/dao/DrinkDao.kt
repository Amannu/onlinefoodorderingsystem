package com.advancedmobileprogramming.yizazun.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.advancedmobileprogramming.yizazun.model.entities.Drink

@Dao
interface DrinkDao {
    @Query("SELECT * FROM drink WHERE  id = :id")
    fun getDrinkById(id: Long): LiveData<Drink>

    @Query("SELECT * FROM drink ORDER BY  id ")
    fun getAllDrink(): LiveData<List<Drink>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrink(drink: Drink)

    @Update
    fun updateDrink(drink: Drink)

    @Delete
    fun deleteDrinkById(drink: Drink)
}