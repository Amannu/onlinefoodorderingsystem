package com.example.yizazun.interfaces

import android.view.View
import com.example.yizazun.data.entity.SpecialOffers

interface SpecialOffersProvider {
    fun onEditClick(view: View, specialOffers: SpecialOffers)
    fun onDeleteClick(specialOffers: SpecialOffers)
}