package com.example.yizazun.interfaces

import android.view.View
import com.example.yizazun.data.entity.Drink

interface DrinkProvider {
    fun onEditClick(view: View, drink: Drink)
    fun onDeleteClick(drink:Drink)
}