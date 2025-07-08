package com.example.simpleloginpage.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simpleloginpage.R
import com.example.simpleloginpage.ui.theme.Typography
import com.example.simpleloginpage.util.Constants
import com.example.simpleloginpage.view.composables.CustomBtn
import com.example.simpleloginpage.view.composables.CustomTextField
import com.example.simpleloginpage.view.composables.NavigationLine
import com.example.simpleloginpage.view.composables.RememberMeRow
import com.example.simpleloginpage.viewmodels.SignupViewModel
import com.example.simpleloginpage.viewmodels.SignupViewModelFactory
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import android.widget.Toast
import com.example.simpleloginpage.model.UserRepoImplementation
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = viewModel(
        factory = SignupViewModelFactory(
            userRepo = UserRepoImplementation
        )
    ),
    onNavigateToLogin: () -> Unit = {},
) {
    val signupState by viewModel.signupState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(viewModel.toastEvent) {
        viewModel.toastEvent.collectLatest { messageResId ->
            Toast.makeText(
                context,
                context.getString(messageResId),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = dimensionResource(R.dimen.content_padding),
            )
            .offset(y = dimensionResource(R.dimen.content_padding_vertical))
    ) {
        Text(
            stringResource(R.string.create_account),
            style = Typography.titleLarge.copy(
                fontSize = 30.sp,
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
        CustomTextField(
            value = signupState.name,
            isError = signupState.nameError != null,
            errorMessage = signupState.nameError?.let { stringResource(it) },
            onValueChange = { viewModel.updateName(it) },
            label = stringResource(R.string.name),
            leadingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = stringResource(R.string.name_icon)
                )
            }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
        CustomTextField(
            value = signupState.email,
            isError = signupState.emailError != null,
            errorMessage = signupState.emailError?.let { stringResource(it) },
            onValueChange = { viewModel.updateEmail(it) },
            label = stringResource(R.string.email),
            leadingIcon = {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = stringResource(R.string.email_icon)
                )
            }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
        CustomTextField(
            value = signupState.password,
            isError = signupState.passwordError != null,
            errorMessage = signupState.passwordError?.let { stringResource(it) },
            onValueChange = { viewModel.updatePassword(it) },
            label = stringResource(R.string.password),
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = stringResource(R.string.lock_icon)
                )
            },
            isPassword = true,
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
        CustomTextField(
            value = signupState.confirmPassword,
            isError = signupState.confirmPasswordError != null,
            errorMessage = signupState.confirmPasswordError?.let { stringResource(it) },
            onValueChange = { viewModel.updateConfirmPassword(it) },
            label = stringResource(R.string.password_confirmation),
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = stringResource(R.string.lock_icon)
                )
            },
            isPassword = true,
            passwordVisible = signupState.isPasswordVisible,
            trailingIcon = {
                IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                    Icon(
                        imageVector = if (signupState.isPasswordVisible)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,
                        contentDescription = if (signupState.isPasswordVisible)
                            stringResource(R.string.hide_password)
                        else
                            stringResource(R.string.show_password),
                        modifier = Modifier.scale(Constants.ICON_SCALE_SMALL)
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))
        RememberMeRow(
            rememberMe = signupState.rememberMe,
            onRememberMeChange = { viewModel.toggleRememberMe() },
            onForgotPasswordClick = {}
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))
        CustomBtn(
            value = if (signupState.isLoading)
                stringResource(R.string.loading)
            else
                stringResource(R.string.sign_up_cap),
            onCLick = {
                viewModel.signup(
                    onSuccess = {}
                )
            },
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))
        NavigationLine(
            leadingValue = stringResource(R.string.already_have_account),
            trailingValue = stringResource(R.string.login_up),
            onClick = { onNavigateToLogin() }
        )
    }

}