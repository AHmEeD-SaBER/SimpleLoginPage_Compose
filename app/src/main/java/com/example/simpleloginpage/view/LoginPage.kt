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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simpleloginpage.R
import com.example.simpleloginpage.viewmodels.MainViewModel
import android.app.Application
import androidx.compose.ui.platform.LocalContext
import com.example.simpleloginpage.model.UserRepoImplementation
import com.example.simpleloginpage.ui.theme.Typography
import com.example.simpleloginpage.util.Constants
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
    val loginState by viewModel.loginState.collectAsState()

    Column {
        Box{
            BackgroundDecoration(
                surface1 = R.drawable.bot_surface,
                surface2 = R.drawable.top_surface,
                surface3 = R.drawable.darken_surface,
                surface4 = R.drawable.top_darken_surface,
                alignment = Alignment.TopStart
            )
            CustomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(R.dimen.app_bar_padding_horizontal),
                        vertical = dimensionResource(R.dimen.app_bar_padding_vertical)
                    ),
                logo = R.drawable.logo,
                actionIcon = R.drawable.menu,
                onMenuClick = {}
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(dimensionResource(R.dimen.content_padding))
            ){
                Column (verticalArrangement = Arrangement.Top){
                    Text(
                        stringResource(R.string.login), 
                        style = Typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))
                    CustomTextField(
                        value = loginState.email,
                        isError = loginState.emailError != null,
                        errorMessage = loginState.emailError,
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
                        value = loginState.password,
                        isError = loginState.passwordError != null,
                        errorMessage = loginState.passwordError,
                        onValueChange = { viewModel.updatePassword(it) },
                        label = stringResource(R.string.password),
                        leadingIcon = { 
                            Icon(
                                Icons.Filled.Lock, 
                                contentDescription = stringResource(R.string.lock_icon)
                            ) 
                        },
                        isPassword = true,
                        passwordVisible = loginState.isPasswordVisible,
                        trailingIcon = {
                            IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                                Icon(
                                    imageVector = if (loginState.isPasswordVisible)
                                                    Icons.Filled.Visibility
                                                  else
                                                    Icons.Filled.VisibilityOff,
                                    contentDescription = if (loginState.isPasswordVisible)
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
                        rememberMe = loginState.rememberMe,
                        onRememberMeChange = { viewModel.toggleRememberMe() },
                        onForgotPasswordClick = {}
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))
                    CustomBtn(
                        value = if (loginState.isLoading)
                                  stringResource(R.string.loading)
                                else 
                                  stringResource(R.string.login_button),
                        onCLick = {
                            viewModel.login(
                                onSuccess = {}
                            )
                        },
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))
                    NavigationLine(
                        leadingValue = stringResource(R.string.no_account),
                        trailingValue = stringResource(R.string.sign_up),
                        onClick = {}
                    )
                }
            }
            BackgroundDecoration(
                surface1 = R.drawable.bottom_bot_surface,
                maxHeight = Constants.BG_BOTTOM_SURFACE_HEIGHT,
                alignment = Alignment.BottomEnd)
        }
    }
}
