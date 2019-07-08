package com.advancedmobileprogramming.yizazun.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.advancedmobileprogramming.yizazun.model.dao.*
import com.advancedmobileprogramming.yizazun.model.entities.*

@Database(entities = arrayOf(Comment::class,Drink::class,Food::class,OrderList::class,User::class),version = 1,exportSchema = false)
abstract class YizazunDatabase:RoomDatabase() {
    abstract fun commentDao():CommentDao
    abstract fun drinkDao():DrinkDao
    abstract fun foodDao():FoodDao
    abstract fun orderListDao():OrderListDao
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
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }

        }
    }
}