package com.advancedmobileprogramming.yizazun.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "food")
class Food (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Long,
    @ColumnInfo(name = "food_name") val food_name:String,
    @ColumnInfo(name = "food_description") val description:String,
    @ColumnInfo(name = "price") val price:Int,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB,name="food_image") var food_image: ByteArray
):Serializable