package com.example.tms_android

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tms_android.databinding.ListItemBinding
import com.example.tms_android.db.User

class MyRecyclerViewAdapter(private val usersList: List<User>,private val clickListener:(User)->Unit) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User,clickListener:(User)->Unit) {
        binding.nameTextView.text = user.name
        binding.lastNameTextView.text = user.lastName
        binding.ageTextView.text = user.age
        binding.listItemLayout.setOnClickListener { clickListener(user) }
    }
}