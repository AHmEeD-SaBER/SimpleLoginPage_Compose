package com.example.simpleloginpage.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simpleloginpage.R
import com.example.simpleloginpage.viewmodels.MainViewModel
import android.app.Application
import androidx.compose.ui.platform.LocalContext
import com.example.simpleloginpage.model.UserRepoImplementation
import com.example.simpleloginpage.viewmodels.ViewModelFactory

@Composable
fun LoginScreen(
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(
            application = LocalContext.current.applicationContext as Application,
            userRepo = UserRepoImplementation()
        )
    ),
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isPasswordVisible by viewModel.isPasswordVisible.collectAsState()
    val rememberMe by viewModel.rememberMe.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column {
        Box{
            ImageSurfaces(
                surface1 = R.drawable.bot_surface,
                surface2 = R.drawable.top_surface,
                surface3 = R.drawable.darken_surface,
                surface4 = R.drawable.top_darken_surface,
                alignment = Alignment.TopStart
            )
            CustomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                logo = R.drawable.logo,
                actionIcon = R.drawable.menu,
                onMenuClick = {}
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(60.dp)
            ){
                Column (verticalArrangement = Arrangement.Top){
                    Text("Login", style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight(900)))
                    Spacer(modifier = Modifier.height(30.dp))
                    CustomTextField(
                        value = email,
                        isError = emailError != null,
                        errorMessage = emailError,
                        onValueChange = { viewModel.updateEmail(it) },
                        label = "Email",
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    CustomTextField(
                        value = password,
                        isError = passwordError != null,
                        errorMessage = passwordError,
                        onValueChange = { viewModel.updatePassword(it) },
                        label = "Password",
                        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
                        isPassword = true,
                        passwordVisible = isPasswordVisible,
                        trailingIcon = {
                            IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                                Icon(
                                    imageVector = if (isPasswordVisible)
                                                    Icons.Filled.Visibility
                                                  else
                                                    Icons.Filled.VisibilityOff,
                                    contentDescription = if (isPasswordVisible)
                                                           "Hide Password"
                                                         else
                                                           "Show Password",
                                    modifier = Modifier.scale(0.7f)
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    RememberMe_Forgot_CustomRow(
                        rememberMe = rememberMe,
                        onRememberMeChange = { viewModel.toggleRememberMe() },
                        onForgotPasswordClick = { viewModel.forgotPassword(
                            onClick = {},
                            onError = {}
                        )}
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    CustomBtn(
                        value = if (isLoading) "LOADING..." else "LOGIN",
                        onCLick = {
                            viewModel.login(
                                onSuccess = {},
                                onError = {}
                            )
                        },
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    NavigationLine(
                        leadingValue = "Don't have an account?",
                        trailingValue = "Sign Up",
                        onClick = {}
                    )
                }
            }
            ImageSurfaces(
                surface1 = R.drawable.bottom_bot_surface,
                maxHeight = 0.64f,
                alignment = Alignment.BottomEnd)
        }
    }
}
