package com.example.yizazun.interfaces

import android.view.View
import com.example.yizazun.data.entity.Food

interface Provider {
    fun onEditClick(view: View, food: Food)
    fun onDeleteClick(food: Food)
}