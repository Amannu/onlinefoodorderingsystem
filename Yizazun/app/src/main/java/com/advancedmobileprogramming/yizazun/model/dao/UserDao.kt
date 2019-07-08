package com.advancedmobileprogramming.yizazun.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.advancedmobileprogramming.yizazun.model.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE  id = :id")
    fun getUserById(id: Long): LiveData<User>

    @Query("SELECT * FROM user ORDER BY  id ")
    fun getAllUser(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUserById(user: User)
}