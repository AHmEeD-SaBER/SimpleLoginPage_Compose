package com.example.simpleloginpage.viewmodels

import android.app.Application
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleloginpage.R
import com.example.simpleloginpage.model.UserRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application, private val userRepo: UserRepo): AndroidViewModel(application) {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun updateEmail(newEmail: String) {
        _loginState.value = _loginState.value.copy(email = newEmail)
        validateEmail()
    }

    fun updatePassword(newPassword: String) {
        _loginState.value = _loginState.value.copy(password = newPassword)
        validatePassword()
    }

    fun togglePasswordVisibility() {
        _loginState.value = _loginState.value.copy(
            isPasswordVisible = !_loginState.value.isPasswordVisible
        )
    }

    fun toggleRememberMe() {
        _loginState.value = _loginState.value.copy(
            rememberMe = !_loginState.value.rememberMe
        )
    }

    private fun validateEmail(): Boolean {
        val email = _loginState.value.email
        return if (email.isEmpty()) {
            _loginState.value = _loginState.value.copy(
                emailError = getApplication<Application>().getString(R.string.email_empty)
            )
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _loginState.value = _loginState.value.copy(
                emailError = getApplication<Application>().getString(R.string.email_invalid)
            )
            false
        } else {
            _loginState.value = _loginState.value.copy(emailError = null)
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = _loginState.value.password
        return if (password.isEmpty()) {
            _loginState.value = _loginState.value.copy(
                passwordError = getApplication<Application>().getString(R.string.password_empty)
            )
            false
        } else if (password.length < 6) {
            _loginState.value = _loginState.value.copy(
                passwordError = getApplication<Application>().getString(R.string.password_too_short)
            )
            false
        } else {
            _loginState.value = _loginState.value.copy(passwordError = null)
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
            _loginState.value = _loginState.value.copy(isLoading = true)
            try {
                delay(1000)
                if(userRepo.login(
                        _loginState.value.email,
                        _loginState.value.password,
                        _loginState.value.rememberMe
                    )) {
                    Toast.makeText(
                        getApplication<Application>().applicationContext,
                        getApplication<Application>().getString(R.string.login_successful),
                        Toast.LENGTH_SHORT
                    ).show()
                    onSuccess()
                    clearForm()
                }
                else{
                    Toast.makeText(
                        getApplication<Application>().applicationContext,
                        getApplication<Application>().getString(R.string.login_failed_invalid),
                        Toast.LENGTH_SHORT
                    ).show()
                    _loginState.value = _loginState.value.copy(isLoading = false)
                }
            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(isLoading = false)
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    getApplication<Application>().getString(R.string.login_failed_with_error, e.message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun clearForm() {
        _loginState.value = LoginState()
    }
}