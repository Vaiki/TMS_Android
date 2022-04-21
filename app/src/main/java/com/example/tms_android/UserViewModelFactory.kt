package com.example.tms_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tms_android.db.UserRepository

class UserViewModelFactory(private val repository: UserRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(UserViewModel::class.java)){
           return UserViewModel(repository)as T
       }
        throw IllegalArgumentException("Unknown View Model class")
    }

}