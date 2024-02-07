package com.example.project.repository

import com.example.project.model.User
import com.example.project.room.UserDao

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(username: String, password: String){
        userDao.insert(User(username= username, password = password))
    }

    suspend fun loginUser(username: String, password: String): User? {
        return userDao.getUser(username, password)
    }
}