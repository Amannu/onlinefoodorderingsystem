package com.example.Yizazun

import com.example.Yizazun.data.SpecialOffer
import com.example.Yizazun.data.User
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class EditSpecialOfferFragmentTest{
    @Test
    fun validEditedSpecialOfferFields(){
        var specialOffer=SpecialOffer(0,"http://test_path","name","test type","test desc",12,
            12.12,Date().toString(),"@testUsername")

        var isValid=EditSpecialOfferFragment.editedSpecialOfferFields(specialOffer)

        assert(isValid)
    }
}