package com.example.simpleloginpage.model

interface UserRepo {
    fun login(email: String, password: String, rememberMe: Boolean): Boolean
    fun signup(name: String, email: String, password: String, rememberMe: Boolean): Boolean
    fun isUserLoggedIn(): Boolean
    fun logout()
}