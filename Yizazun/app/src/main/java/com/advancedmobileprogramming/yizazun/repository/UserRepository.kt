package com.advancedmobileprogramming.yizazun.repository


import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.User
import com.advancedmobileprogramming.yizazun.model.UserDao
import com.advancedmobileprogramming.yizazun.network.UserYizazunApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


class UserRepository () {
    private val userDao: UserDao? = null
    fun findUserById(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<LiveData<User>> = UserYizazunApiService.getInstance().findUserByIdAsync(id).await()
                if (!response.isSuccessful) {
                userDao?.getUserByUserId(id)}
            }
            finally {
                println("successful")
            }

        }
    }

    fun insertUser(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Void> = UserYizazunApiService.getInstance().insertUserAsync(user).await()
                if (!response.isSuccessful) {
                    userDao?.insertUser(user)
                }
            }
            finally {
                println("successful")
            }
        }
    }
    fun updateUser(id: Long,user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Void> = UserYizazunApiService.getInstance().updateUserAsnc(id,user).await()
                if (!response.isSuccessful) {
                    userDao?.updateUser(id,user)
                }
            }
            finally {
                println("successful")
            }
        }
    }
    fun deleteUser(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Void> = UserYizazunApiService.getInstance().deleteUserAsync(id).await()
                if (!response.isSuccessful) {
                    userDao?.deleteUser(id)
                }
            }
            finally {
                println("successful")
            }
        }
    }
}