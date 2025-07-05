package com.example.simpleloginpage.model

interface UserRepo {
    fun login(email: String, password: String, rememberMe: Boolean): Boolean
    fun register(email: String, password: String): Boolean
    fun isUserLoggedIn(): Boolean
    fun logout()
}