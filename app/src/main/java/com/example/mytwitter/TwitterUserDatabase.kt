package com.example.mytwitter

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TwitterUser::class], version = 1, exportSchema = false)
abstract class TwitterUserDatabase : RoomDatabase() {
    abstract fun TwitterUserDao(): TwitterUserDao

    companion object {
        private var INSTANCE: TwitterUserDatabase? = null
        private val lock = Any()
        fun getInstance(context: Context): TwitterUserDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            TwitterUserDatabase::class.java, "User.db")
                            .allowMainThreadQueries()
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}
