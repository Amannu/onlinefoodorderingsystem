package com.example.yizazun.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.yizazun.data.dao.DrinkDao
import com.example.yizazun.data.entity.Drink
import com.example.yizazun.services.DrinkApiServices
import com.example.yizazun.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class DrinkRepository (private  val drinkDao: DrinkDao, private  val drinkApiServices: DrinkApiServices, private  val application: Application){
    fun getDrinkById(id:Int): LiveData<Drink> {
        return drinkDao.getDrinkById(id)
    }
    fun getAllDrink(): LiveData<List<Drink>> {

        //Check if we are opining the app for first time and the database is null or empty
        //This part is done only once as you told me in the office

        val drink = drinkDao.getDrinks().value
        if(drink == null || drink.isEmpty()){
            refreshAllDrink()
        }
        return drinkDao.getDrinks()
    }

    fun insertDrink(drink: Drink){
        drinkApiServices.insertDrinksAsync(drink)
    }

    fun updateDrink(drink: Drink){
        drinkApiServices.updateDrinksAsync(drink.id,drink)
        drinkDao.updateDrinks(drink)
    }

    fun deleteDrink(drink: Drink){
        if(Constant.connected(application)){
            drinkApiServices.deleteDrinksByIdAsync(drink.id)
            drinkDao.deleteDrinks(drink)
        }else{
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(application,"Your are not connected", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun refreshAllDrink(){
        if(Constant.connected(application)){
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<List<Drink>> = drinkApiServices.findAllDrinksAsync().await()
                    val drinks:List<Drink>? = response.body()
                    if(drinks!=null){
                        for (f in drinks){
                            drinkDao.insertDrinks(f)
                        }
                    }
                }
            }catch (e: Exception){

            }
        }

    }
}