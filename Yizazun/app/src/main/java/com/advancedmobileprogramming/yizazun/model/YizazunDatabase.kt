package com.advancedmobileprogramming.yizazun.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class, Order::class, User::class],version = 1,exportSchema = false)
abstract class YizazunDatabase:RoomDatabase() {
    abstract fun foodDao():FoodDao
    abstract fun orderDao():OrderDao
    abstract fun userDao():UserDao

    companion object {

        @Volatile
        private var INSTANCE: YizazunDatabase? = null

        fun getDatabase(context: Context): YizazunDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YizazunDatabase::class.java, "yizazun_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}