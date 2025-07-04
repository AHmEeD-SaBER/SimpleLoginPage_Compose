package com.example.simpleloginpage.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isPasswordVisible by mutableStateOf(false)
        private set

    var rememberMe by mutableStateOf(false)
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun updateEmail(newEmail: String) {
        email = newEmail
        validateEmail()
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
        validatePassword()
    }

    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    fun toggleRememberMe() {
        rememberMe = !rememberMe
    }

    private fun validateEmail(): Boolean {
        return if (email.isEmpty()) {
            emailError = "Email cannot be empty"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Invalid email format"
            false
        } else {
            emailError = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        return if (password.isEmpty()) {
            passwordError = "Password cannot be empty"
            false
        } else if (password.length < 6) {
            passwordError = "Password must be at least 6 characters"
            false
        } else {
            passwordError = null
            true
        }
    }

    fun login(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()

        if (!isEmailValid || !isPasswordValid) {
            return
        }

        viewModelScope.launch {
            isLoading = true
            try {
                delay(1000)
                isLoading = false
                onSuccess()
                clearForm()
            } catch (e: Exception) {
                isLoading = false
                onError(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun forgotPassword(onClick: () -> Unit, onError: (String) -> Unit) {
        if (!validateEmail()) {
            onError("Please enter a valid email address")
            return
        }

        viewModelScope.launch {
            isLoading = true
            try {
                delay(1000)
                isLoading = false
                onClick()
            } catch (e: Exception) {
                isLoading = false
                onError(e.message ?: "Unknown error occurred")
            }
        }
    }

    private fun clearForm() {
        email = ""
        password = ""
        isPasswordVisible = false
        emailError = null
        passwordError = null
    }
}