package com.example.simpleloginpage.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleloginpage.R
import com.example.simpleloginpage.model.UserRepo
import com.example.simpleloginpage.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignupViewModel(private val userRepo: UserRepo): ViewModel() {
    private val _signupState = MutableStateFlow(SignupState())
    val signupState: StateFlow<SignupState> = _signupState.asStateFlow()

    private val _toastEvent = MutableSharedFlow<Int>()
    val toastEvent: SharedFlow<Int> = _toastEvent.asSharedFlow()

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
            nameIsEmpty()
        } else {
            nameIsValid()
        }
    }

    private fun validateEmail(): Boolean {
        val email = _signupState.value.email
        return if (email.isEmpty()) {
            emailIsEmpty()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailIsInvalid()
        } else {
            emailIsValid()
        }
    }

    private fun validatePassword(): Boolean {
        val password = _signupState.value.password
        return if (password.isEmpty()) {
            passwordIsEmpty()
        } else if (password.length < Constants.PASSWORD_MIN_LENGTH) {
            passwordTooShort()
        } else {
            passwordIsValid()
        }
    }

    private fun validateConfirmPassword(): Boolean {
        val password = _signupState.value.password
        val confirmPassword = _signupState.value.confirmPassword

        return if (confirmPassword.isEmpty()) {
            confirmPasswordIsEmpty()
        } else if (password != confirmPassword) {
            passwordsDontMatch()
        } else {
            confirmPasswordIsValid()
        }
    }

    private fun nameIsEmpty(): Boolean {
        setNameError(R.string.name_empty)
        return false
    }

    private fun nameIsValid(): Boolean {
        setNameError(null)
        return true
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

    private fun passwordIsEmpty(): Boolean {
        setPasswordError(R.string.password_empty)
        return false
    }

    private fun passwordTooShort(): Boolean {
        setPasswordError(R.string.password_too_short)
        return false
    }

    private fun passwordIsValid(): Boolean {
        setPasswordError(null)
        return true
    }

    private fun confirmPasswordIsEmpty(): Boolean {
        setConfirmPasswordError(R.string.password_confirmation_empty)
        return false
    }

    private fun passwordsDontMatch(): Boolean {
        setConfirmPasswordError(R.string.passwords_dont_match)
        return false
    }

    private fun confirmPasswordIsValid(): Boolean {
        setConfirmPasswordError(null)
        return true
    }

    private fun setNameError(errorRes: Int?) {
        _signupState.value = _signupState.value.copy(nameError = errorRes)
    }

    private fun setEmailError(errorRes: Int?) {
        _signupState.value = _signupState.value.copy(emailError = errorRes)
    }

    private fun setPasswordError(errorRes: Int?) {
        _signupState.value = _signupState.value.copy(passwordError = errorRes)
    }

    private fun setConfirmPasswordError(errorRes: Int?) {
        _signupState.value = _signupState.value.copy(confirmPasswordError = errorRes)
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
                delay(1000)
                if(userRepo.signup(
                    _signupState.value.name,
                    _signupState.value.email,
                    _signupState.value.password,
                    _signupState.value.rememberMe
                )) {
                    _toastEvent.emit(R.string.signup_successful)
                    onSuccess()
                    clearForm()
                }
                else {
                    _signupState.value = _signupState.value.copy(isLoading = false)
                    _toastEvent.emit(R.string.signup_failed)
                }
            } catch (e: Exception) {
                _signupState.value = _signupState.value.copy(isLoading = false)
                _toastEvent.emit(R.string.signup_failed_with_error)
            }
        }
    }

    private fun clearForm() {
        _signupState.value = SignupState()
    }

}