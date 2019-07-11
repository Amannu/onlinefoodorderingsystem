package com.example.Yizazun

import com.example.Yizazun.data.Food
import com.example.Yizazun.data.User
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class EditFoodFragmentTest{
    @Test
    fun validEditedFoodFields(){
        var food=Food(0,"http://test_path","name","test type","test desc",12,
            12.12,Date().toString(),"@testUsername")

        var isValid=EditFoodFragment.editedFoodFields(food)

        assert(isValid)
    }
}