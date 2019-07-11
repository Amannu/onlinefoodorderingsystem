package com.example.yizazun.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "announcement")
data class Announcement(@PrimaryKey val id:Int,
                         @ColumnInfo(name = "subject")val subject:String,
                         @ColumnInfo(name = "description")val description:String): Serializable