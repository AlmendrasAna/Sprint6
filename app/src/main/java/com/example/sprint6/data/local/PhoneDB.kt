package com.example.sprint6.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[PhoneDataEntity::class], version =1)
abstract class PhoneDB : RoomDatabase(){



    abstract fun getDaoPhone(): PhoneDao


    companion object {
        @Volatile
        private var INSTANCE: PhoneDB? = null

        fun getDatabase(context: Context):PhoneDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDB::class.java,
                    "Phone_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}