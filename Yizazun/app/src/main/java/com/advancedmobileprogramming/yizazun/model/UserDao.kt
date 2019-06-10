package com.advancedmobileprogramming.yizazun.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * from user")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * From user WHERE id=:userId")
    fun getUserByUserId(userId:Long): LiveData<User>

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(id:Long)

    @Update
    fun updateUser(id:Long,user: User)
}