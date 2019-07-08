package com.advancedmobileprogramming.yizazun.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.dao.UserDao
import com.advancedmobileprogramming.yizazun.model.entities.User
import com.advancedmobileprogramming.yizazun.model.network.UserApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class UserRepository (private val userDao: UserDao, private val userApiService: UserApiService, private val application: Application){

    suspend fun getUserById(id:Long): LiveData<User> = userDao.getUserById(id)

    suspend fun getAllUsers(): LiveData<List<User>>{

        val user = userDao.getAllUser().value
       // if(user == null || user.isEmpty()){
            refreshAllUsers()
       // }
        return userDao.getAllUser()
    }

    suspend fun insertUser(user: User) {
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            userApiService.postUsers(user)
            userDao.insertUser(user)
        }

    }

    suspend fun deleteUser(user: User){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            userApiService.deleteUsers(user.id)
            userDao.deleteUserById(user)
        }
    }

    suspend fun updateUser(user: User){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            userApiService.updateUsers(user.id,user)
            userDao.updateUser(user)
        }
    }

    suspend fun refreshAllUsers(){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<List<User>> = userApiService.getUsers().await()
                    val users:List<User>? = response.body()
                    if(users!=null){
                        for (user in users){
                            userDao.insertUser(user)
                        }
                    }
                }
            }catch (e: Exception){

            }
        }

    }
}