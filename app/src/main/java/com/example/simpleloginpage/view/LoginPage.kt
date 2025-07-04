package com.example.simpleloginpage.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    onLoginSuccess: () -> Unit = {},
    onNavigateTo: () -> Unit = {}
) {
    Column {
        Box{
            ImageSurfaces(
                surface1 = R.drawable.bot_surface,
                surface2 = R.drawable.top_surface,
                surface3 = R.drawable.darken_surface,
                alignment = Alignment.TopStart
            )
            CustomAppBar(
                barModifier = Modifier.fillMaxWidth(),
                iconModifier = Modifier
                    .padding(30.dp, 50.dp)
                    .size(50.dp)
                    .scale(0.8f),
                logo = R.drawable.logo,
                actionIcon = R.drawable.menu,
                onClick = { /* Handle click */ }
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
                        value = viewModel.email,
                        isError = viewModel.emailError != null,
                        errorMessage = viewModel.emailError,
                        onValueChange = { viewModel.updateEmail(it) },
                        label = "Email",
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    CustomTextField(
                        value = viewModel.password,
                        isError = viewModel.passwordError != null,
                        errorMessage = viewModel.passwordError,
                        onValueChange = { viewModel.updatePassword(it) },
                        label = "Password",
                        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
                        isPassword = true,
                        passwordVisible = viewModel.isPasswordVisible,
                        trailingIcon = {
                            IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                                Icon(
                                    imageVector = if (viewModel.isPasswordVisible)
                                                    Icons.Filled.Visibility
                                                  else
                                                    Icons.Filled.VisibilityOff,
                                    contentDescription = if (viewModel.isPasswordVisible)
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
                        rememberMe = viewModel.rememberMe,
                        onRememberMeChange = { viewModel.toggleRememberMe() },
                        onForgotPasswordClick = { viewModel.forgotPassword(
                            onClick = { /* Show success message */ },
                            onError = { /* Show error message */ }
                        )}
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    CustomBtn(
                        value = if (viewModel.isLoading) "LOADING..." else "LOGIN",
                        onCLick = {
                            viewModel.login(
                                onSuccess = onLoginSuccess,
                                onError = { /* Show error message */ }
                            )
                        },
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    NavigationLine(
                        leadingValue = "Don't have an account?",
                        trailingValue = "Sign Up",
                        onClick = onNavigateTo
                    )
                }
            }
            ImageSurfaces(surface1 = R.drawable.bottom_bot_surface, alignment = Alignment.BottomEnd)
        }
    }
}
