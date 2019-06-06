package com.advancedmobileprogramming.yizazun.model

import androidx.room.*
import java.io.Serializable

@Entity (tableName = "order_list")
class Order (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Long,
    @ColumnInfo(name = "quantity") val quantity:Int,
    @ColumnInfo(name = "user_id") val uid:Long,
    @ColumnInfo(name = "food_id") val fid:Long
    ):Serializable