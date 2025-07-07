package com.example.simpleloginpage.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleloginpage.R
import com.example.simpleloginpage.model.UserRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepo: UserRepo): ViewModel() {
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
            emailIsEmpty()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailIsInvalid()
        } else {
            emailIsValid()
        }
    }

    private fun validatePassword(): Boolean {
        val password = _loginState.value.password
        return if (password.isEmpty()) {
            passwordIsEmpty()
        } else if (password.length < 6) {
            passwordNotMatchLength()
        } else {
            passwordIsValid()
        }
    }

    private fun emailIsEmpty(): Boolean {
        setEmailError(R.string.email_empty)
        return false
    }

    private fun emailIsInvalid(): Boolean {
        setEmailError(R.string.email_invalid)
        return false
    }

    private fun emailIsValid(): Boolean {
        setEmailError(null)
        return true
    }

    private fun setEmailError(errorRes: Int?) {
        _loginState.value = _loginState.value.copy(emailError = errorRes)
    }

    private fun passwordNotMatchLength() : Boolean{
        _loginState.value = _loginState.value.copy(
            passwordError = R.string.password_too_short
        )
        return false
    }

    private fun passwordIsValid(): Boolean {
        _loginState.value = _loginState.value.copy(passwordError = null)
        return true
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
                    _loginState.value = _loginState.value.copy(
                        toastMessageId = R.string.login_successful
                    )
                    onSuccess()
                    clearForm()
                }
                else{
                    _loginState.value = _loginState.value.copy(
                        isLoading = false,
                        toastMessageId = R.string.login_failed_invalid
                    )
                }
            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(
                    isLoading = false,
                    toastMessageId = R.string.login_failed_with_error
                )
            }
        }
    }

    private fun passwordIsEmpty(): Boolean {
        _loginState.value = _loginState.value.copy(
            passwordError = R.string.password_empty
        )
        return false
    }

    private fun clearForm() {
        _loginState.value = LoginState()
    }
}