package com.advancedmobileprogramming.yizazun.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "order")
class Order (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "order_id") val id:Int,
    @ColumnInfo(name = "first_name") val fname:String,
    @ColumnInfo(name = "last_name") val lname:String,
    @ColumnInfo(name = "address") val address:String,
    @ColumnInfo(name = "phone_number") val pnumber:Int,
    @ColumnInfo(name = "food_name") val food_name:String
):Serializable