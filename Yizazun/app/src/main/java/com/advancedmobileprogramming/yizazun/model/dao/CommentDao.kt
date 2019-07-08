package com.advancedmobileprogramming.yizazun.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.advancedmobileprogramming.yizazun.model.entities.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment WHERE  id = :id")
    fun getCommentById(id: Long): LiveData<Comment>

    @Query("SELECT * FROM comment ORDER BY  id ")
    fun getCommentDrink(): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: Comment)

    @Update
    fun updateComment(comment: Comment)

    @Delete
    fun deleteCommentById(comment: Comment)
}