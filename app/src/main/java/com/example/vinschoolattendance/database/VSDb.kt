package com.example.vinschoolattendance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = arrayOf())
abstract class VSDb: RoomDatabase() {
abstract fun dbDao(): DbDao

    companion object {
        private var INSTANCE: VSDb?= null
        private val DB_NAME = "VSDb"

        fun getDatabase(context: Context): VSDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VSDb::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}