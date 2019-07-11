package com.example.yizazun.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "drink")
data class Drink(@PrimaryKey val id:Int,
                @ColumnInfo(name = "name")val name:String,
                @ColumnInfo(name = "content")val content:String): Serializable