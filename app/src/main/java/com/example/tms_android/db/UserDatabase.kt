package com.example.tms_android.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDAO:UserDAO

    companion object{
        @Volatile
        private var INSTANCE:UserDatabase?=null
        fun getInstance(context: Context):UserDatabase{
            synchronized(this){
                var instance:UserDatabase? = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "USER_DATABASE"
                    ).build()
                }
                return instance
            }
        }
    }
}