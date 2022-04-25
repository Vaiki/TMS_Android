package com.example.tms_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.example.tms_android.databinding.ActivityMainBinding
import com.example.tms_android.db.User
import com.example.tms_android.db.UserDatabase
import com.example.tms_android.db.UserRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding.myViewModel = userViewModel
        binding.lifecycleOwner = this//т.к. используем liveData
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        displayUserList()
    }

    private fun displayUserList() {
        userViewModel.users.observe(this, Observer {
            Log.i("MYTAG", it.toString())
            binding.userRecyclerView.adapter =
                MyRecyclerViewAdapter(it) { selectedItem: User -> listItemClicked(selectedItem) }
        })
    }

    private fun listItemClicked(user: User) {
        Toast.makeText(this, "selected name is ${user.name}", Toast.LENGTH_LONG).show()
    userViewModel.initUpdateOrDelete(user)
    }
}