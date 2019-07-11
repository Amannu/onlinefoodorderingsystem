package com.example.Yizazun

import com.example.Yizazun.data.Food
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class InsertFoodTest{

    @Test
    fun validInsertFoodFields(){
        var food= Food(0,"http://test_path","name","test type","test desc",12,
            12.12, Date().toString(),"@testUsername")

        var isValid=FoodInsertFragment.allFoodFieldsSpecified(food)
        assert(isValid)
    }
}