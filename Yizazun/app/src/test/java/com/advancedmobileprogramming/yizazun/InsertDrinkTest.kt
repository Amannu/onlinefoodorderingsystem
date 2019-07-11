package com.example.Yizazun

import com.example.Yizazun.data.Drink
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class InsertDrinkTest{

    @Test
    fun validInsertDrinkFields(){
        var drink= Drink(0,"http://test_path","name","test type","test desc",12,
            12.12, Date().toString(),"@testUsername")

        var isValid=DrinkInsertFragment.allDrinkFieldsSpecified(drink)
        assert(isValid)
    }
}