package com.example.simpleloginpage.viewmodels

import android.app.Application
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleloginpage.model.UserRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val userRepo: UserRepo): AndroidViewModel(application) {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _isPasswordVisible = MutableStateFlow(false)
    private val _rememberMe = MutableStateFlow(false)
    private val _emailError = MutableStateFlow<String?>(null)
    private val _passwordError = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)

    val email: StateFlow<String> = _email.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val isPasswordVisible: StateFlow<Boolean> = _isPasswordVisible.asStateFlow()
    val rememberMe: StateFlow<Boolean> = _rememberMe.asStateFlow()
    val emailError: StateFlow<String?> = _emailError.asStateFlow()
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        validateEmail()
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        validatePassword()
    }

    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }

    fun toggleRememberMe() {
        _rememberMe.value = !_rememberMe.value
    }

    private fun validateEmail(): Boolean {
        return if (_email.value.isEmpty()) {
            _emailError.value = "Email cannot be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
            _emailError.value = "Invalid email format"
            false
        } else {
            _emailError.value = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        return if (_password.value.isEmpty()) {
            _passwordError.value = "Password cannot be empty"
            false
        } else if (_password.value.length < 6) {
            _passwordError.value = "Password must be at least 6 characters"
            false
        } else {
            _passwordError.value = null
            true
        }
    }

    fun login(onSuccess: () -> Unit) {
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()

        if (!isEmailValid || !isPasswordValid) {
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                delay(1000)
                _isLoading.value = false
//                userRepo.register(email.value,password.value)
                if(userRepo.login(email.value, password.value, rememberMe.value)) {
                    Toast.makeText(
                        getApplication<Application>().applicationContext,
                        "Login successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    onSuccess()
                    clearForm()
                }
                else{
                    Toast.makeText(
                        getApplication<Application>().applicationContext,
                        "Login failed: Invalid credentials",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                _isLoading.value = false
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    "Login failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun clearForm() {
        _email.value = ""
        _password.value = ""
        _isPasswordVisible.value = false
        _emailError.value = null
        _passwordError.value = null
    }
}