package com.advancedmobileprogramming.yizazun.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "user")
class User (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Long,
    @ColumnInfo(name = "user_name") val uname:String,
    @ColumnInfo(name = "password") val password:String,
    @ColumnInfo(name = "address") val address:String,
    @ColumnInfo(name = "phone_number") val pnumber:String,
    @ColumnInfo(name = "role") val role:String
):Serializable