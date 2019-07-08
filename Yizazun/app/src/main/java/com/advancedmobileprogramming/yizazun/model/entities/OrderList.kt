package com.advancedmobileprogramming.yizazun.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_list")
data class OrderList (@PrimaryKey(autoGenerate = true) var id: Long, @ColumnInfo(name = "item_name") val item_name: String,
                 @ColumnInfo(name = "full_name") val full_name: String,  @ColumnInfo(name = "adress") val adress: String)