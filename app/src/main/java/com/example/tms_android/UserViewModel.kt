package com.example.tms_android

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_android.db.User
import com.example.tms_android.db.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel(), Observable {
    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete:User

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputLastName = MutableLiveData<String?>()

    @Bindable
    val inputAge = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if(isUpdateOrDelete){
            userToUpdateOrDelete.name = inputName.value!!
            userToUpdateOrDelete.lastName = inputLastName.value!!
            userToUpdateOrDelete.age = inputAge.value!!
            update(userToUpdateOrDelete)
        }else {
            val name = inputName.value!!
            val lastName = inputLastName.value!!
            val age = inputAge.value!!
            insert(User(0, name, lastName, age))
            inputName.value = null
            inputLastName.value = null
            inputAge.value = null
        }

    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete){
            delete(userToUpdateOrDelete)
        }else{clearAll()}

    }

    private fun insert(user: User): Job = viewModelScope.launch { repository.insert(user) }

    fun update(user: User): Job = viewModelScope.launch { repository.update(user)
        inputName.value = null
        inputLastName.value = null
        inputAge.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear all"
    }

    fun delete(user: User): Job = viewModelScope.launch { repository.delete(user)
        inputName.value = null
        inputLastName.value = null
        inputAge.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear all"}

    private fun clearAll(): Job = viewModelScope.launch { repository.deleteAll() }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
fun initUpdateOrDelete(user:User){
    inputName.value = user.name
    inputLastName.value = user.lastName
    inputAge.value = user.age
    isUpdateOrDelete = true
    userToUpdateOrDelete = user
    saveOrUpdateButtonText.value = "Update"
    clearAllOrDeleteButtonText.value = "Delete"
}
}