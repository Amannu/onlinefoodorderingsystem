package com.example.Yizazun

import com.example.Yizazun.data.SpecialOffer
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class InsertSpecialOfferTest{

    @Test
    fun validInsertSpecialOfferFields(){
        var specialOffer= SpecialOffer(0,"http://test_path","name","test type","test desc",12,
            12.12, Date().toString(),"@testUsername")

        var isValid=SpecialOfferInsertFragment.allSpecialOfferFieldsSpecified(specialOffer)
        assert(isValid)
    }
}