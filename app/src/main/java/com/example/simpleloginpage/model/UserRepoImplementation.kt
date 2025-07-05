package com.example.simpleloginpage.model

class UserRepoImplementation : UserRepo {
    private val users = mutableListOf<User>()
    private var loggedInUser: User? = null

    override fun login(email: String, password: String, rememberMe: Boolean): Boolean {
        val user = users.find { it.email == email && it.password == password }
        return if (user != null) {
            loggedInUser = user
            true
        } else {
            false
        }
    }

    override fun register(email: String, password: String): Boolean {
        if (users.any { it.email == email }) {
            return false
        }
        users.add(User(email, password))
        return true
    }

    override fun isUserLoggedIn(): Boolean {
        return loggedInUser != null
    }

    override fun logout() {
        loggedInUser = null
    }
}