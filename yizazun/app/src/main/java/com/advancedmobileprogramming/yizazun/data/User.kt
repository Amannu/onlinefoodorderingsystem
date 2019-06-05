package com.advancedmobileprogramming.yizazun.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "users")
class User (
    @PrimaryKey @ColumnInfo(name = "user_name") val uname:String,
    @ColumnInfo(name = "first_name") val fname:String,
    @ColumnInfo(name = "last_name") val lname:String,
    @ColumnInfo(name = "password") val password:String,
    @ColumnInfo(name = "address") val address:String,
    @ColumnInfo(name = "phone_number") val pnumber:Int,
    @ColumnInfo(name = "balance") val balance:Double,
    @ColumnInfo(name = "role") val role:Int
):Serializable