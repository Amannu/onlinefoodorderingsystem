package com.example.yizazun.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yizazun.data.dao.AnnouncementDao
import com.example.yizazun.data.dao.DrinkDao
import com.example.yizazun.data.dao.FoodDao
import com.example.yizazun.data.entity.*

@Database(entities = [Food::class,Drink::class,Announcement::class,SpecialOffers::class], version = 2,exportSchema = false)
abstract class YizazunDatabase:RoomDatabase() {
    abstract fun foodDao():FoodDao
    abstract fun drinkDao():DrinkDao
    abstract fun announcementDao():AnnouncementDao
    abstract fun specialOffersDao():AnnouncementDao
    companion object {
        @Volatile
        private var INSTANCE: YizazunDatabase? = null

        fun getDatabase(context: Context):YizazunDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YizazunDatabase::class.java,"yizazun_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}