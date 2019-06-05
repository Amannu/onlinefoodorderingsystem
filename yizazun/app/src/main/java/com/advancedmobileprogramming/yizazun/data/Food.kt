package com.advancedmobileprogramming.yizazun.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "food")
class Food (
    @PrimaryKey @ColumnInfo(name = "food_name") val food_name:String,
    @ColumnInfo(name = "description") val description:String,
    @ColumnInfo(name = "price") val price:Double,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB,name="food_image") var food_image: ByteArray
):Serializable