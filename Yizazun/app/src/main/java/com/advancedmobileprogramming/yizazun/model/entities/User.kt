package com.advancedmobileprogramming.yizazun.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (@PrimaryKey(autoGenerate = true) var id: Long, @ColumnInfo(name = "user_name") val user_name: String,
            @ColumnInfo(name = "password") val password: String, @ColumnInfo(name = "role") val role: String)

