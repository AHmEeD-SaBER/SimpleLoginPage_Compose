package com.example.simpleloginpage.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.simpleloginpage.R
import com.example.simpleloginpage.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = { },
    logo: Int,
    actionIcon: Int
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(logo),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier
                    .size(dimensionResource(R.dimen.logo_size))
                    .scale(Constants.ICON_SCALE_NORMAL),
            )
        },
        actions = {
            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.size(dimensionResource(R.dimen.app_bar_menu_button_size))
            ) {
                Image(
                    painter = painterResource(actionIcon),
                    contentDescription = stringResource(R.string.menu),
                    modifier = Modifier.size(dimensionResource(R.dimen.app_bar_menu_icon_size))
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}
