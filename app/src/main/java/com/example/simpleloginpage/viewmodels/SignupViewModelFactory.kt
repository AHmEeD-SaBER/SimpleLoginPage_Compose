package com.example.simpleloginpage.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleloginpage.model.UserRepo

class SignupViewModelFactory(
    private val application: Application,
    private val userRepo: UserRepo
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(application, userRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

