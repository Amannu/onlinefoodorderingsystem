package com.advancedmobileprogramming.yizazun.model.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food (@PrimaryKey(autoGenerate = true) var id: Long, @ColumnInfo(name = "food_name") val food_name: String,
            @ColumnInfo(name = "price") var price: Int)
