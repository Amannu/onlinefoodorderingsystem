package com.advancedmobileprogramming.yizazun.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.dao.DrinkDao
import com.advancedmobileprogramming.yizazun.model.entities.Drink
import com.advancedmobileprogramming.yizazun.model.network.DrinkApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class DrinkRepository (private val drinkDao: DrinkDao, private val drinkApiService: DrinkApiService, private val application: Application) {
    suspend fun getDrinkById(id:Long): LiveData<Drink> = drinkDao.getDrinkById(id)

    suspend fun getAllFood(): LiveData<List<Drink>> {

        val drink = drinkDao.getAllDrink().value
       // if(drink == null || drink.isEmpty()){
            refreshAllDrinks()
       // }
        return drinkDao.getAllDrink()
    }

    suspend fun insertDrink(drink: Drink) {
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            drinkApiService.postDrinks(drink)
            drinkDao.insertDrink(drink)
        }

    }

    suspend fun deleteDrink(drink: Drink){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            drinkApiService.deleteDrinks(drink.id)
            drinkDao.deleteDrinkById(drink)
        }
    }

    suspend fun updateDrink(drink: Drink){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            drinkApiService.updateDrinks(drink.id,drink)
            drinkDao.updateDrink(drink)
        }
    }
    fun refreshAllDrinks(){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo =connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<List<Drink>> = drinkApiService.getDrinks().await()
                    val drinks:List<Drink>? = response.body()
                    if(drinks!=null){
                        for (drink in drinks){
                            drinkDao.insertDrink(drink)
                        }
                    }
                }
            }catch (e: Exception){

            }
        }

    }
}