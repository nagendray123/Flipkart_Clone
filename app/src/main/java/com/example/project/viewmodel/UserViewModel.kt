package com.example.project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.model.User
import com.example.project.repository.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(private val userRepository: UserRepository) : ViewModel(){
    private val _loggedInUser = MutableLiveData<User>()
    val loggedInUser: LiveData<User> get() = _loggedInUser

    fun registerUser(username: String, password: String){
        viewModelScope.launch {
            userRepository.registerUser(username, password)
        }
    }

    fun loginUser(username: String, password: String){
        viewModelScope.launch {
            _loggedInUser.value = userRepository.loginUser(username, password)
        }
    }



}