package com.example.simpleloginpage.model

data class User(
    val email: String,
    val password: String,
    val name: String = ""
)
