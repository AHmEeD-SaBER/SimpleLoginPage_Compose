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

class SignupViewModel(application: Application, private val userRepo: UserRepo): AndroidViewModel(application) {
    private val _signupState = MutableStateFlow(SignupState())
    val signupState: StateFlow<SignupState> = _signupState.asStateFlow()

    fun updateName(newName: String) {
        _signupState.value = _signupState.value.copy(name = newName)
        validateName()
    }

    fun updateEmail(newEmail: String) {
        _signupState.value = _signupState.value.copy(email = newEmail)
        validateEmail()
    }

    fun updatePassword(newPassword: String) {
        _signupState.value = _signupState.value.copy(password = newPassword)
        validatePassword()
        validateConfirmPassword()
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        _signupState.value = _signupState.value.copy(confirmPassword = newConfirmPassword)
        validateConfirmPassword()
    }

    fun togglePasswordVisibility() {
        _signupState.value = _signupState.value.copy(
            isPasswordVisible = !_signupState.value.isPasswordVisible
        )
    }

    fun toggleRememberMe() {
        _signupState.value = _signupState.value.copy(
            rememberMe = !_signupState.value.rememberMe
        )
    }

    private fun validateName(): Boolean {
        val name = _signupState.value.name
        return if (name.isEmpty()) {
            _signupState.value = _signupState.value.copy(
                nameError = getApplication<Application>().getString(R.string.name_empty)
            )
            false
        } else {
            _signupState.value = _signupState.value.copy(nameError = null)
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = _signupState.value.email
        return if (email.isEmpty()) {
            _signupState.value = _signupState.value.copy(
                emailError = getApplication<Application>().getString(R.string.email_empty)
            )
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _signupState.value = _signupState.value.copy(
                emailError = getApplication<Application>().getString(R.string.email_invalid)
            )
            false
        } else {
            _signupState.value = _signupState.value.copy(emailError = null)
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = _signupState.value.password
        return if (password.isEmpty()) {
            _signupState.value = _signupState.value.copy(
                passwordError = getApplication<Application>().getString(R.string.password_empty)
            )
            false
        } else if (password.length < 6) {
            _signupState.value = _signupState.value.copy(
                passwordError = getApplication<Application>().getString(R.string.password_too_short)
            )
            false
        } else {
            _signupState.value = _signupState.value.copy(passwordError = null)
            true
        }
    }

    private fun validateConfirmPassword(): Boolean {
        val password = _signupState.value.password
        val confirmPassword = _signupState.value.confirmPassword

        return if (confirmPassword.isEmpty()) {
            _signupState.value = _signupState.value.copy(
                confirmPasswordError = getApplication<Application>().getString(R.string.password_confirmation_empty)
            )
            false
        } else if (password != confirmPassword) {
            _signupState.value = _signupState.value.copy(
                confirmPasswordError = getApplication<Application>().getString(R.string.passwords_dont_match)
            )
            false
        } else {
            _signupState.value = _signupState.value.copy(confirmPasswordError = null)
            true
        }
    }

    fun signup(onSuccess: () -> Unit) {
        val isNameValid = validateName()
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()
        val isConfirmPasswordValid = validateConfirmPassword()

        if (!isNameValid || !isEmailValid || !isPasswordValid || !isConfirmPasswordValid) {
            return
        }

        viewModelScope.launch {
            _signupState.value = _signupState.value.copy(isLoading = true)
            try {
                delay(1000) // Simulate network request
                if(userRepo.signup(
                    _signupState.value.name,
                    _signupState.value.email,
                    _signupState.value.password,
                    _signupState.value.rememberMe
                )) {
                    Toast.makeText(
                        getApplication<Application>().applicationContext,
                        getApplication<Application>().getString(R.string.signup_successful),
                        Toast.LENGTH_SHORT
                    ).show()
                    onSuccess()
                    clearForm()
                }
                else {
                    Toast.makeText(
                        getApplication<Application>().applicationContext,
                        getApplication<Application>().getString(R.string.signup_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                    _signupState.value = _signupState.value.copy(isLoading = false)
                }
            } catch (e: Exception) {
                _signupState.value = _signupState.value.copy(isLoading = false)
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    getApplication<Application>().getString(R.string.signup_failed_with_error, e.message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun clearForm() {
        _signupState.value = SignupState()
    }
}