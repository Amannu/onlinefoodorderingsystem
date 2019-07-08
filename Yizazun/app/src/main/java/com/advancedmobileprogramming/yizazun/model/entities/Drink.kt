package com.advancedmobileprogramming.yizazun.model.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink")
class Drink (@PrimaryKey(autoGenerate = true) var id: Long, @ColumnInfo(name = "drink_name") val drink_name: String,
             @ColumnInfo(name = "price") var price: Int
)