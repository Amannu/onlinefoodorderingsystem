package com.example.Yizazun

import com.example.Yizazun.data.Drink
import com.example.Yizazun.data.User
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class EditDrinkFragmentTest{
    @Test
    fun validEditedDrinkFields(){
        var drink=Drink(0,"http://test_path","name","test type","test desc",12,
            12.12,Date().toString(),"@testUsername")

        var isValid=EditDrinkFragment.editedDrinkFields(drink)

        assert(isValid)
    }
}