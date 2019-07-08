package com.advancedmobileprogramming.yizazun.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
class Comment(@PrimaryKey(autoGenerate = true) var id: Long, @ColumnInfo(name = "description") val description: String,
              @ColumnInfo(name = "subject") val subject: String)