package com.advancedmobileprogramming.yizazun.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * from users ORDER BY first_name")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * From users WHERE user_name=:userName")
    fun getUserByUserName(userName:String): LiveData<Order>

    @Insert
    fun insertOrder(user: User)

    @Delete
    fun deleteFood(user: User)

    @Update
    fun updateFood(user: User)
}