package com.example.yizazun.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.example.yizazun.R
import com.example.yizazun.data.YizazunDatabase
import com.example.yizazun.data.entity.Food
import com.example.yizazun.repository.FoodRepository
import com.example.yizazun.services.FoodApiServices
import com.example.yizazun.utility.Constant
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodViewModel (application: Application) : AndroidViewModel(application) {
    private val foodRepository:FoodRepository
    val name = MutableLiveData<String>("")
    val content = MutableLiveData<String>("")
    val nid = MutableLiveData<Int>(-1)
    private val context: Application
    init {
        foodRepository= FoodRepository((YizazunDatabase.getDatabase(application).foodDao()),FoodApiServices.getInstance(),application )
        context = application
    }
    fun getAllFood(): LiveData<List<Food>> {
        return foodRepository.getAllFood()
    }
    fun deleteFood(food: Food) = viewModelScope.launch(Dispatchers.IO){
        foodRepository.deleteFood(food)
    }
    fun getNewsById(id:Int): LiveData<Food> {
        return foodRepository.getFoodById(id)
    }

    fun onFABFood(view: View){
        Navigation.findNavController(view).navigate(R.id.action_authorFragment_to_postNewsFragment)
    }
    fun onPostFoodButtonClicked(view: View){
        val button = view as CircularProgressButton
        if (Constant.connected(context)) {
            button.startAnimation()
            GlobalScope.launch(Dispatchers.IO) {
                foodRepository.insertFood(Food(-1,name.toString(),content.toString()))
               withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Posted Successfully", Toast.LENGTH_SHORT).show()
                    clear()
                    button.revertAnimation()
                }
            }

        } else {
            Snackbar.make(view, "Your are not connected, Try Again", Snackbar.LENGTH_SHORT).show()
        }
    }
    fun onUpdateFoodButtonClicked(view: View){
        val button = view as CircularProgressButton
        if (Constant.connected(context)) {
            button.startAnimation()
            GlobalScope.launch(Dispatchers.IO) {

                foodRepository.updateFood(Food(nid.value.toString().toInt(), name.value.toString(), content.value.toString()))
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show()
                    clear()
                    button.revertAnimation()
                }
            }

        } else {
            Snackbar.make(view, "Your are not connected, Try Again", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun clear(){
        name.value = ""
        content.value= ""
    }

}