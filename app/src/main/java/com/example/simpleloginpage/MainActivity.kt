package com.example.simpleloginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simpleloginpage.util.Constants
import com.example.simpleloginpage.navigation.Routes
import com.example.simpleloginpage.view.LoginScreen
import com.example.simpleloginpage.view.SignupScreen
import com.example.simpleloginpage.view.composables.BackgroundDecoration
import com.example.simpleloginpage.view.composables.CustomAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Box {
                BackgroundDecoration(
                    baseSurface = R.drawable.bot_surface,
                    topSurface = R.drawable.top_surface,
                    darkenSurface = R.drawable.darken_surface,
                    topDarkenSurface = R.drawable.top_darken_surface,
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

                NavHost(navController = navController, startDestination = Routes.LOGIN) {
                    composable(Routes.LOGIN) {
                        LoginScreen(
                            onNavigateToSignup = { navController.navigate(Routes.SIGNUP) }
                        )
                    }
                    composable(Routes.SIGNUP) {
                        SignupScreen(
                            onNavigateToLogin = { navController.popBackStack() }
                        )
                    }
                }

                BackgroundDecoration(
                    baseSurface = R.drawable.bottom_bot_surface,
                    maxHeight = Constants.BG_BOTTOM_SURFACE_HEIGHT,
                    alignment = Alignment.BottomEnd)
            }
        }

    }
}