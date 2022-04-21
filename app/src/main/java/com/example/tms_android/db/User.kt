package com.example.tms_android.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER_TABLE")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="user_id")
    var id:Int,
    @ColumnInfo(name="user_name")
    var name:String,
    @ColumnInfo(name="user_lastName")
    var lastName:String,
    @ColumnInfo(name="user_age")
    var age:String)