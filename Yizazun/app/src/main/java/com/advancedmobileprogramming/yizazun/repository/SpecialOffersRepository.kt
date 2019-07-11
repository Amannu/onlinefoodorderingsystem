package com.example.yizazun.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.yizazun.data.dao.SpecialOffersDao
import com.example.yizazun.data.entity.SpecialOffers
import com.example.yizazun.services.SpecialOffersApiServices
import com.example.yizazun.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class SpecialOffersRepository( private val specialOffersDao:SpecialOffersDao,private  val specialOffersApiServices:SpecialOffersApiServices,private  val application: Application) {
    fun getSpecialOffersById(id: Int): LiveData<SpecialOffers> {
        return specialOffersDao.getSpecialOffersById(id)
    }

    fun getAllSpecialOffers(): LiveData<List<SpecialOffers>> {

        //Check if we are opining the app for first time and the database is null or empty
        //This part is done only once as you told me in the office
        val special = specialOffersDao.getSpecialOffers().value
        if (special == null || special.isEmpty()) {
            refreshAllOffers()
        }
        return specialOffersDao.getSpecialOffers()
    }

    fun insertOffers(specialOffers: SpecialOffers) {
        specialOffersApiServices.insertSpecialOfferssAsync(specialOffers)
    }

    fun updateOffers(specialOffers: SpecialOffers) {
        specialOffersApiServices.updateSpecialOfferssAsync(specialOffers.id, specialOffers)
        specialOffersDao.updateSpecialOffers(specialOffers)
    }

    fun deleteOffers(specialOffers: SpecialOffers) {
        if (Constant.connected(application)) {
            specialOffersApiServices.deleteSpecialOffersByIdAsync(specialOffers.id)
            specialOffersDao.deleteSpecialOffers(specialOffers)
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(application, "Your are not connected", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun refreshAllOffers() {
        if (Constant.connected(application)) {
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<List<SpecialOffers>> =
                        specialOffersApiServices.findAllSpecialOffersAsync().await()
                    val offers: List<SpecialOffers>? = response.body()
                    if (offers != null) {
                        for (f in offers) {
                            specialOffersDao.insertSpecialOffers(f)
                        }
                    }
                }
            } catch (e: Exception) {

            }
        }


    }
}