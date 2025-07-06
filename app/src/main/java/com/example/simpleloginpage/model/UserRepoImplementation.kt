package com.example.simpleloginpage.model

class UserRepoImplementation private constructor() : UserRepo {
    private val users = mutableListOf<User>()
    private var loggedInUser: User? = null

    companion object {
        @Volatile
        private var instance: UserRepoImplementation? = null

        fun getInstance(): UserRepoImplementation {
            return instance ?: synchronized(this) {
                instance ?: UserRepoImplementation().also { instance = it }
            }
        }
    }

    override fun login(email: String, password: String, rememberMe: Boolean): Boolean {
        val user = users.find { it.email == email && it.password == password }
        return if (user != null) {
            loggedInUser = user
            true
        } else {
            false
        }
    }

    override fun signup(name: String, email: String, password: String, rememberMe: Boolean): Boolean {
        if (users.any { it.email == email }) {
            return false
        }
        users.add(User(email, password, name))
        if (rememberMe) {
            loggedInUser = users.last()
        }
        return true
    }

    override fun isUserLoggedIn(): Boolean {
        return loggedInUser != null
    }

    override fun logout() {
        loggedInUser = null
    }
}