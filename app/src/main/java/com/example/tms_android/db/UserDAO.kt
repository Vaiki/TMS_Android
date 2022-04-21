package com.example.tms_android.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {
    @Insert
    suspend fun insertUser(user:User)
    @Update
    suspend fun updateUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)
    @Query("DELETE FROM USER_TABLE")
    suspend fun deleteAll()
    @Query("SELECT * FROM USER_TABLE")
    fun getAllUsers():LiveData<List<User>>
}